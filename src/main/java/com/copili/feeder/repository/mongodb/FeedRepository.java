package com.copili.feeder.repository.mongodb;

import com.copili.feeder.domain.Feed;
import com.copili.feeder.domain.FeedEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedRepository extends MongoRepository<Feed, String> {

    Feed findOneByUrl(String url);

}
