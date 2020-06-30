package com.jparandag.ml.challenge.controller;

import com.jparandag.ml.challenge.service.MutantService;
import com.jparandag.ml.challenge.utils.Validation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/magneto/api/v1")
@AllArgsConstructor
@Slf4j
public class MutantResource {

    private MutantService mutantService;

    @PostMapping("/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DNARequest dnaRequest) {
        if(!Validation.validateRequest(dnaRequest)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        String[] dnaArray = new String[dnaRequest.getDna().size()];
        dnaArray = dnaRequest.getDna().toArray(dnaArray);
        boolean isMutantResult = mutantService.isMutant(dnaArray);

        if (!isMutantResult) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<DNAStatisticResponse> retrieveStatistics() {
        Optional<DNAStatisticResponse> statistic = Optional.ofNullable(mutantService.retrieveStatistics());
        if(!statistic.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.of(statistic);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllData() {
        mutantService.deleteAll();
    }
}
