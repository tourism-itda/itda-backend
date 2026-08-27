package com.tourism.itda.explore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.explore.data.PersonImageData;
import com.tourism.itda.explore.entity.Person;
import lombok.Getter;

@Getter
public class PersonResponse {

    @JsonProperty("person_id")
    private final Long personId;

    private final String name;
    private final String description;
    private final String summary;
    private final String kingdom;
    private final String type;

    @JsonProperty("image_url")
    private final String imageUrl;

    public PersonResponse(Person person) {
        this.personId = person.getPersonId();
        this.name = person.getName();
        this.description = person.getDescription();
        this.summary = person.getSummary();
        this.kingdom = person.getKingdom().name();
        this.type = person.getType().name();
        this.imageUrl = PersonImageData.getImageUrl(person);
    }
}