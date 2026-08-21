package com.tourism.itda.explore.dto;

import com.tourism.itda.explore.entity.Person;
import lombok.Getter;

@Getter
public class PersonResponse {

    private final Long personId;
    private final String name;
    private final String description;
    private final String kingdom;
    private final String type;

    public PersonResponse(Person person) {
        this.personId = person.getPersonId();
        this.name = person.getName();
        this.description = person.getDescription();
        this.kingdom = person.getKingdom().name();
        this.type = person.getType().name();
    }
}