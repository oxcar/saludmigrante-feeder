package com.copili.feeder.service.impl;

import com.copili.feeder.domain.Feed;
import com.copili.feeder.domain.FeedEntry;
import com.copili.feeder.domain.FilterWord;
import com.copili.feeder.domain.adapter.FeedEntryAdapter;
import com.copili.feeder.repository.mongodb.FeedEntryRepository;
import com.copili.feeder.repository.mongodb.FilterWordRepository;
import com.copili.feeder.service.FeedService;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service("feedService")
public class FeedServiceImpl implements FeedService {

    private final static Logger log = LoggerFactory.getLogger(FeedService.class);

    @Autowired
    private FeedEntryRepository feedEntryRepository;

    @Autowired
    private FilterWordRepository filterWordRepository;

    private Tokenizer tokenizer;

    private SentenceDetector sentenceDetector;

    public FeedServiceImpl() {
        try {
            Resource englishTokenModelResource = new ClassPathResource("en-token.bin");
            InputStream is = englishTokenModelResource.getInputStream();
            TokenizerModel englishTokenModel = new TokenizerModel(is);
            tokenizer = new TokenizerME(englishTokenModel);
            is.close();

            Resource englishSentenceModelresource = new ClassPathResource("en-sent.bin");
            InputStream is2 = englishSentenceModelresource.getInputStream();
            SentenceModel englishSentenceModel = new SentenceModel(is2);
            sentenceDetector = new SentenceDetectorME(englishSentenceModel);
            is2.close();

        } catch (Exception e) {
            log.error("Error al inicializar FeedService");
        }
    }

    @Override
    public Date retrieveFeedEntries(Feed feed) {
        Date start = new Date();
        List<FeedEntry> feedEntries = parseFeed(feed);
        List<FeedEntry> filteredFeedEntries = filterFeedEntries(feedEntries);
        Date lastUpdate = new Date(feed.getLastProcessedFeedEntry().getTime());
        for (FeedEntry feedEntry : filteredFeedEntries) {
            if (feedEntry.getPublishedDate().compareTo(lastUpdate) > 0) {
                lastUpdate = feedEntry.getPublishedDate();
            }
            if (feedEntry.getPublishedDate().compareTo(feed.getLastProcessedFeedEntry()) > 0) {
                FeedEntry previousFeedEntry = feedEntryRepository.findOneByUri(feedEntry.getUri());
                if (null == previousFeedEntry) {
                    feedEntry.setHits(feedEntry.getMatchedWords().size());
                    feedEntryRepository.save(feedEntry);
                    updateStatistics(feedEntry.getMatchedWords());
                }
            }
        }
        Date end = new Date();
        log.info("Time to process Feed: " + (end.getTime() - start.getTime()) + "ms");
        return lastUpdate;
    }

    private void updateStatistics(List<String> matchedWords) {
        for (String matchedWord : matchedWords) {
            FilterWord filterWord = filterWordRepository.findOneByWord(matchedWord);
            if (null != filterWord) {
                filterWord.incrementHits(1L);
                filterWordRepository.save(filterWord);
            }
        }
    }

    private List<FeedEntry> parseFeed(Feed feed) {
        List<FeedEntry> feedEntries = new ArrayList<>();
        try {
            log.info("Retrieving feed " + feed.getUrl());
            SyndFeedInput input = new SyndFeedInput();
            XmlReader xmlReader = new XmlReader(new URL(feed.getUrl()));
            SyndFeed syndFeed = input.build(xmlReader);
            for (SyndEntry entry : (List<SyndEntry>) syndFeed.getEntries()) {
                FeedEntry feedEntry = FeedEntryAdapter.fromSyndEntry(entry, feed);
                if (feedEntry.getPublishedDate().compareTo(feed.getLastProcessedFeedEntry()) > 0) {
                    feedEntries.add(feedEntry);
                }
            }
        } catch (MalformedURLException e) {
            log.error("Error al parsear la URL del feed");
        } catch (IOException e) {
            log.error("Error al leer el feed desde la URL");
        } catch (FeedException e) {
            log.error("Error al procesar el feed");
        } catch (Exception e) {
            log.error("Error desconocido");
        }
        return feedEntries;
    }

    private List<FeedEntry> filterFeedEntries(List<FeedEntry> feedEntries) {
        List<FeedEntry> filteredFeedEntries = new ArrayList<>();
        for (FeedEntry feedEntry : feedEntries) {
            String text = extractArticleContentFromUri(feedEntry.getUri());
            String tokens[] = tokenizer.tokenize(text);
            String[] matchedWords = matchedWords(tokens, filterWordRepository.findByEnabled(true));
            if (ArrayUtils.isNotEmpty(matchedWords)) {
                CollectionUtils.addAll(feedEntry.getMatchedWords(), matchedWords);
                String[] sentences = sentenceDetector.sentDetect(text);
                String[] matchedSentences = matchedSentences(sentences, matchedWords);
                CollectionUtils.addAll(feedEntry.getMatchedPhrases(), matchedSentences);
                filteredFeedEntries.add(feedEntry);
            }
        }
        return filteredFeedEntries;
    }

    private String[] matchedWords(String[] tokens, List<FilterWord> filterWords) {
        Set<String> matches = new HashSet<>();
        for (FilterWord filterWord : filterWords) {
            String word = filterWord.getWord();
            if (StringUtils.isNotBlank(word) && ArrayUtils.contains(tokens, word)) {
                log.info("Matches word " + word);
                matches.add(word);
            }
        }
        return matches.toArray(new String[matches.size()]);
    }

    private String[] matchedSentences(String[] sentences, String[] words) {
        Set<String> matches = new HashSet<>();
        for (String sentence : sentences) {
            for (String word : words) {
                if (StringUtils.isNotBlank(sentence) && sentence.contains(word)) {
                    log.info("Matches sentence " + sentence);
                    matches.add(sentence);
                }
            }
        }
        return matches.toArray(new String[matches.size()]);
    }

    private String extractArticleContentFromUri(String uri) {
        if (StringUtils.isBlank(uri)) {
            return "";
        }
        try {
            final HTMLDocument htmlDoc = HTMLFetcher.fetch(new URL(uri));
            final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
            String content = ArticleExtractor.INSTANCE.getText(doc);
            return StringUtils.isNotBlank(content) ? content : "";
        } catch (Exception e) {
            log.error("Error al extraer el contenido de la URI " + uri);
        }
        return "";
    }

}
