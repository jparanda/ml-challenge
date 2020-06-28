package com.jparandag.ml.challenge.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

@Value
@JsonPropertyOrder({"countMutant", "countHuman", "ratio"})
public class DNAStatisticResponse {

    @JsonProperty("count_mutant_dna")
    int countMutant;

    @JsonProperty("count_human_dna")
    int countHuman;

    double ratio;

    public static DNAStatisticResponse createNewObject(int countMutant, int countHuman, double ratio) {
        return new DNAStatisticResponse(countMutant, countHuman, ratio);
    }


}
