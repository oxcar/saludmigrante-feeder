package com.copili.feeder.cron;

import com.copili.feeder.domain.Feed;
import com.copili.feeder.repository.mongodb.FeedRepository;
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

    @Scheduled(fixedDelay = 10000)
    public void processFeeds() {
        log.info("Process Feeds - Start");
        List<Feed> feeds = feedRepository.findAll();
        for(Feed feed : feeds) {
            Date lastUpdate = feedService.retrieveFeedEntries(feed);
            feed.setLastProcessedFeedEntry(lastUpdate);
            feedRepository.save(feed);
        }
        log.info("Process Feeds - End");
    }

}
