package com.jparandag.ml.challenge.service;

import com.jparandag.ml.challenge.controller.DNAStatisticResponse;

public interface MutantService {

    boolean isMutant(String[] dna);

    DNAStatisticResponse retrieveStatistics();

    void deleteAll();
}
