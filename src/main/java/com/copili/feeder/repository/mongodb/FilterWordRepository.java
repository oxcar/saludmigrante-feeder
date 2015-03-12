package com.copili.feeder.repository.mongodb;

import com.copili.feeder.domain.FilterWord;
import com.copili.feeder.domain.State;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FilterWordRepository extends MongoRepository<FilterWord, String> {

    FilterWord findOneByWord(String word);

    List<FilterWord> findByEnabled(Boolean enabled);

}
