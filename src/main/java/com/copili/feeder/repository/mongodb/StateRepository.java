package com.copili.feeder.repository.mongodb;

import com.copili.feeder.domain.State;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StateRepository extends MongoRepository<State, String> {

}
