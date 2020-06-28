package com.jparandag.ml.challenge.repository;

import com.jparandag.ml.challenge.model.Statistic;
import com.jparandag.ml.challenge.utils.MagnetoUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class MutantRepositoryTest {

    private static final String[] DNA_1= {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    private static final String[] DNA_2_SAME_1= {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};

    @Autowired
    private MutantRepository mutantRepository;

    @BeforeEach
    public void initSetup() {
        String dna_1 = MagnetoUtils.StringArrayToString(DNA_1);
        String dna_2 = MagnetoUtils.StringArrayToString(DNA_2_SAME_1);
        Statistic statistic_1 = Statistic.createNewObject(dna_1, true);
        Statistic statistic_2 = Statistic.createNewObject(dna_2, true);
        mutantRepository.save(statistic_1);
        mutantRepository.save(statistic_2);
    }

    @Test
    public void findAll_result_not_empty() {
        List<Statistic> resultStatistic = mutantRepository.findAll();
        Assertions.assertThat(resultStatistic).isNotEmpty();
    }

    @Test
    public void findAll_only_save_one_record_by_DNA() {
        List<Statistic> resultStatistic = mutantRepository.findAll();
        Assertions.assertThat(resultStatistic).hasSize(1);
    }

}