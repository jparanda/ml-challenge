package com.jparandag.ml.challenge.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jparandag.ml.challenge.controller.DNARequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.ldap.LdapName;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    private static final String dnaRequestJson_valid = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";
    private static final String dnaRequestJson_null = "{\"dna\":null}";
    private static final String dnaRequestJson_empty = "{\"dna\":[]}";
    private static final String dnaRequestJson_bad_dna_sequence = "{\"dna\":[\"ATHCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";

    private DNARequest dnaRequest_valid, dnaRequest_null, dnaRequest_empty, dnaRequest_bad_dna_sequence;

    @BeforeEach
    public void initSetup() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        dnaRequest_valid = mapper.readValue(dnaRequestJson_valid, DNARequest.class);
        dnaRequest_null = mapper.readValue(dnaRequestJson_null, DNARequest.class);
        dnaRequest_empty = mapper.readValue(dnaRequestJson_empty, DNARequest.class);
        dnaRequest_bad_dna_sequence = mapper.readValue(dnaRequestJson_bad_dna_sequence, DNARequest.class);
    }

    @Test
    void validateRequest_valid_request() {
        Boolean resultActualValidation = Validation.validateRequest(dnaRequest_valid);
        Assertions.assertTrue(resultActualValidation);
    }

    @Test
    void validateRequest_invalid_request_null() {
        Boolean resultActualValidation = Validation.validateRequest(dnaRequest_null);
        Assertions.assertFalse(resultActualValidation);
    }

    @Test
    void validateRequest_invalid_request_empty() {
        Boolean resultActualValidation = Validation.validateRequest(dnaRequest_empty);
        Assertions.assertFalse(resultActualValidation);
    }

    @Test
    void validateRequest_invalid_request_bad_dna_sequence() {
        Boolean resultActualValidation = Validation.validateRequest(dnaRequest_bad_dna_sequence);
        Assertions.assertFalse(resultActualValidation);
    }
}