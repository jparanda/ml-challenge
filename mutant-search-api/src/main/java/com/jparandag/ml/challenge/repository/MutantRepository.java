package com.jparandag.ml.challenge.repository;

import com.jparandag.ml.challenge.model.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MutantRepository extends MongoRepository<Statistic, String> {
}
