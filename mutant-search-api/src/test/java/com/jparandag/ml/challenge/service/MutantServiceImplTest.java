package com.jparandag.ml.challenge.service;

import com.jparandag.ml.challenge.controller.DNAStatisticResponse;
import com.jparandag.ml.challenge.model.Statistic;
import com.jparandag.ml.challenge.repository.MutantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MutantServiceImplTest {

    private static final String[] DNA_MUTANT = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    private static final String[] DNA_HUMAN = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};

    @MockBean
    private MutantRepository mutantRepository;

    @Autowired
    private MutantService mutantService;


    @Test
    void isMutant_dna_is_for_mutant() {
        boolean result = mutantService.isMutant(DNA_MUTANT);
        Assertions.assertTrue(result);
    }

    @Test
    void isMutant_dna_is_for_human() {
        boolean result = mutantService.isMutant(DNA_HUMAN);
        Assertions.assertFalse(result);
    }

    @Test
    void retrieveStatistics_repository_return_null() {
        Mockito.when(mutantRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        DNAStatisticResponse actualResponse = mutantService.retrieveStatistics();
        Assertions.assertNull(actualResponse);
    }

    @Test
    void retrieveStatistic_repository_return_data() {
        Mockito.when(mutantRepository.findAll()).thenReturn(buildStatisticMockList());
        DNAStatisticResponse actualResponse = mutantService.retrieveStatistics();
        Assertions.assertEquals(2, actualResponse.getCountHuman());
        Assertions.assertEquals(2, actualResponse.getCountMutant());
    }

    private List<Statistic> buildStatisticMockList() {
        Statistic statistic_1 = Statistic.createNewObject("ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG", true);
        Statistic statistic_2 = Statistic.createNewObject("ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG", true);

        Statistic statistic_3 = Statistic.createNewObject("ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG", false);
        Statistic statistic_4 = Statistic.createNewObject("ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG", false);


        List<Statistic> statistics = new ArrayList<>();
        statistics.add(statistic_1);
        statistics.add(statistic_2);
        statistics.add(statistic_3);
        statistics.add(statistic_4);

        return statistics;

    }

}