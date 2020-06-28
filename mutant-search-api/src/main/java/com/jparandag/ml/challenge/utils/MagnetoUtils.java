package com.jparandag.ml.challenge.utils;

import java.util.Arrays;

public class MagnetoUtils {

    public static String StringArrayToString(String[] dna) {
        StringBuilder strDna = new StringBuilder();
        Arrays.asList(dna).stream().forEach(strDna::append);
        return strDna.toString();
    }
}
