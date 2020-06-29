package com.jparandag.ml.challenge.service;

import com.jparandag.ml.challenge.controller.DNAStatisticResponse;
import com.jparandag.ml.challenge.model.Statistic;
import com.jparandag.ml.challenge.repository.MutantRepository;
import com.jparandag.ml.challenge.utils.MagnetoUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MutantServiceImpl implements MutantService {

    private MutantRepository mutantRepository;

    @Override
    public boolean isMutant(String[] dna) {
        final boolean resultValidationDNA = validateDNA(dna);
        Statistic newObject = Statistic.createNewObject(MagnetoUtils.StringArrayToString(dna), resultValidationDNA);
        mutantRepository.save(newObject);
        return resultValidationDNA;
    }

    @Override
    public DNAStatisticResponse retrieveStatistics() {
        if(!mutantRepository.findAll().isEmpty()) {
            return processStatistics(mutantRepository.findAll());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        mutantRepository.deleteAll();
    }

    private boolean validateDNA(String[] dna) {

        int dnaFined = 0;
        for (int i = 0 ; i < dna.length ; i++) {
            for (int j = 0 ; j < dna.length ; j++) {

                //Checking horizontal
                if(j < dna.length - 3) {

                    if (compareValues(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2),
                            dna[i].charAt(j + 3))) {
                        dnaFined++;
                    }
                }

                //Checking vertical
                if (i < dna.length  - 3) {
                    if (compareValues(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j),
                            dna[i + 3].charAt(j))) {
                        dnaFined++;
                    }
                }

                //Checking oblicuo
                if ((i < dna.length  - 3) && (j < dna.length  - 3)){
                    if (compareValues(dna[i].charAt(j), dna[i + 1].charAt(j + 1), dna[i + 2].charAt(j + 2),
                            dna[i + 3].charAt(j + 3))) {
                        dnaFined++;
                    }

                    if (compareValues(dna[i].charAt((dna.length - 1) - j), dna[i + 1].charAt((dna.length - 2) - j), dna[i + 2].charAt((dna.length - 3) - j),
                            dna[i + 3].charAt((dna.length - 4) - j))) {
                        dnaFined++;
                    }
                }

                if(dnaFined >= 2 ) {
                   return true;
                }
            }
        }
        return false;
    }

    private boolean compareValues(char a, char b, char c, char d) {
        return a == b && b == c && c == d;
    }

    private DNAStatisticResponse processStatistics(List<Statistic> statistics) {
        int person = 0;
        int mutant = 0;

        for (Statistic statistic: statistics) {
            if(statistic.isMutant()){
                mutant++;
            }
            else {
                person++;
            }
        }
        double ratio = calculateRatio(person, mutant);

        return DNAStatisticResponse.createNewObject(mutant, person, ratio);
    }

    private double calculateRatio(int person, int mutant) {
        if(person != 0) {
            return  mutant/person;
        }
        return mutant;
    }
}
