package com.jparandag.ml.challenge.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MagnetoUtilsTest {

    private static final String[] DNA_MUTANT = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    private static final String EXPECTED_CONVERSION = "ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG";

    @Test
    void stringArrayToString() {
        String actualStringConversion = MagnetoUtils.StringArrayToString(DNA_MUTANT);
        Assertions.assertEquals(EXPECTED_CONVERSION, actualStringConversion);
    }
}