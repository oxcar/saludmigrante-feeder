package com.copili.feeder.service;

import com.copili.feeder.domain.Feed;

import java.util.Date;

public interface FeedService {

    Date retrieveFeedEntries(Feed feed);

}
