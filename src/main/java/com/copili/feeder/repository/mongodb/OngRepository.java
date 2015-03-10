package com.copili.feeder.repository.mongodb;

import com.copili.feeder.domain.Copi;
import com.copili.feeder.domain.Ong;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OngRepository extends MongoRepository<Ong, String> {

}
