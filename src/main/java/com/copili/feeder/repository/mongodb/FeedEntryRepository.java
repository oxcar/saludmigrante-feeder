package com.copili.feeder.repository.mongodb;

import com.copili.feeder.domain.FeedEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedEntryRepository extends MongoRepository<FeedEntry, String> {

    FeedEntry findOneByUri(String uri);

    FeedEntry findOneByFeedIdAndId(String feedId, String id);

}
