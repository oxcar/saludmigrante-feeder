package com.copili.feeder.cron;

import com.copili.feeder.domain.Feed;
import com.copili.feeder.domain.FeedEntry;
import com.copili.feeder.domain.FilterWord;
import com.copili.feeder.repository.mongodb.FeedEntryRepository;
import com.copili.feeder.repository.mongodb.FeedRepository;
import com.copili.feeder.repository.mongodb.FilterWordRepository;
import com.copili.feeder.service.FeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RetrieveFeedsCronJob {

    private final static Logger log = LoggerFactory.getLogger(RetrieveFeedsCronJob.class);

    @Autowired
    private FeedService feedService;

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private FeedEntryRepository feedEntryRepository;

    @Autowired
    private FilterWordRepository filterWordRepository;

    @Scheduled(fixedDelay = 10 /*minutos*/ * 60 /*segundos*/ * 1000 /*milisegundos*/)
    public void processFeeds() {
        log.info("Process Feeds - Start");
        List<Feed> feeds = feedRepository.findAll();
        for (Feed feed : feeds) {
            Date lastUpdate = feedService.retrieveFeedEntries(feed);
            feed.setLastProcessedFeedEntry(lastUpdate);
            feedRepository.save(feed);
        }
        log.info("Process Feeds - End");
    }

    /*
    @Scheduled(fixedDelay = 99999999) Esto ya no se va a usar
    public void calculateHits() {
        log.info("Calculate Hits - Start");
        for(FeedEntry feedEntry : feedEntryRepository.findAll()) {
            feedEntry.setHits(feedEntry.getMatchedWords().size());
            feedEntryRepository.save(feedEntry);
        }
        log.info("Calculate Hits - End");
    }
    */

}
