package com.jparandag.ml.challenge.model;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Value
public class Statistic {

    @Id
    String id;
    boolean isMutant;

    public static Statistic createNewObject(String id, boolean isPerson){
        return new Statistic(id, isPerson);
    }
}
