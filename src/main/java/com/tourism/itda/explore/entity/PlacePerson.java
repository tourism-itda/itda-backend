package com.tourism.itda.explore.entity;

import com.tourism.itda.place.entity.Place;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "place_person")
@Getter
@NoArgsConstructor
@IdClass(PlacePerson.PlacePersonId.class)
public class PlacePerson {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    public PlacePerson(Place place, Person person) {
        this.place = place;
        this.person = person;
    }

    @EqualsAndHashCode
    @NoArgsConstructor
    public static class PlacePersonId implements Serializable {

        private Long place;
        private Long person;
    }
}