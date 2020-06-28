package com.jparandag.ml.challenge.utils;

import com.jparandag.ml.challenge.controller.DNARequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final String REGEX_EXPRESSION = "^[ATCG]{6,6}$";
    private static final Pattern DNA_PATTERN = Pattern.compile(REGEX_EXPRESSION);

    public static boolean validateRequest(DNARequest dnaRequest) {
        if(dnaRequest.getDna() == null || dnaRequest.getDna().isEmpty()) {
            return false;
        }

        for (String str : dnaRequest.getDna()) {
            Matcher matcher = DNA_PATTERN.matcher(str);
            if (!matcher.find()) {
                return false;
            }
        }
        return true;
    }

    private Validation(){}
}
