package com.copili.feeder.domain.adapter;

import com.copili.feeder.domain.Feed;
import com.copili.feeder.domain.FeedEntry;
import com.google.common.base.Strings;
import com.sun.syndication.feed.synd.SyndEntry;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import java.util.Date;
import java.util.List;

public class FeedEntryAdapter {

    public static FeedEntry fromSyndEntry(SyndEntry syndEntry, Feed feed) {
        if (null == syndEntry) {
            return null;
        }
        String source = feed.getName() + " - " + feed.getUrl();
        String link = Strings.nullToEmpty(syndEntry.getLink()).trim();
        String title = Strings.nullToEmpty(syndEntry.getTitle()).trim();
        String description = Strings.nullToEmpty(null != syndEntry.getDescription() ? syndEntry.getDescription().getValue() : "").trim();
        Date publishedDate = null != syndEntry.getPublishedDate() ? syndEntry.getPublishedDate() : new Date(0);

        FeedEntry feedEntry = new FeedEntry(feed.getId(), link, title, description, publishedDate, source);

        List<Element> elements = (List<Element>) syndEntry.getForeignMarkup();
        if (CollectionUtils.isNotEmpty(elements)) {
            for (Element element : elements) {
                if (StringUtils.isNotBlank(element.getAttributeValue("url"))) {
                    feedEntry.getThumbnails().add(element.getAttributeValue("url"));
                }
            }
        }
        return feedEntry;
    }

}
