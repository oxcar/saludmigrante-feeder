package com.copili.feeder.repository.mongodb;

import com.copili.feeder.domain.Copi;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CopiRepository extends MongoRepository<Copi, String> {

    Copi findOneByUrl(String url);

}
