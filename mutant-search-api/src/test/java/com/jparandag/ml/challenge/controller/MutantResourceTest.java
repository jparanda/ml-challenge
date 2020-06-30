package com.jparandag.ml.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jparandag.ml.challenge.service.MutantService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MutantResource.class)
class MutantResourceTest {

    private static final String DNA_MUTANT_REQUEST = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCCA\",\"TCACTG\"]}";

    private static final String DNA_HUMAN_REQUEST = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"]}";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MutantService mutantService;

    @Test
    void give_mutant_dna_request_then_return_http_200() throws Exception {
        Mockito.when(mutantService.isMutant(Mockito.any())).thenReturn(Boolean.TRUE);
        mvc.perform(MockMvcRequestBuilders
                .post("/magneto/api/v1/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(DNA_MUTANT_REQUEST)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void give_human_dna_request_then_return_http_403() throws Exception {
        Mockito.when(mutantService.isMutant(Mockito.any())).thenReturn(Boolean.FALSE);
        mvc.perform(MockMvcRequestBuilders
                .post("/magneto/api/v1/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(DNA_HUMAN_REQUEST)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void return_data_for_statistics() throws Exception {
        Mockito.when(mutantService.retrieveStatistics()).thenReturn(buildStatisticResponse());
        mvc.perform(MockMvcRequestBuilders
                .get("/magneto/api/v1/stats")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count_mutant_dna").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.count_human_dna").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ratio").value(1));
    }

    private DNAStatisticResponse buildStatisticResponse() {
        return DNAStatisticResponse.createNewObject(2,1,1);
    }

}