package com.tourism.itda.explore.entity;

import com.tourism.itda.explore.enums.Kingdom;
import com.tourism.itda.place.entity.Place;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "place_kingdom")
@Getter
@NoArgsConstructor
@IdClass(PlaceKingdom.PlaceKingdomId.class)
public class PlaceKingdom {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "kingdom")
    private Kingdom kingdom;

    public PlaceKingdom(Place place, Kingdom kingdom) {
        this.place = place;
        this.kingdom = kingdom;
    }

    @EqualsAndHashCode
    @NoArgsConstructor
    public static class PlaceKingdomId implements Serializable {

        private Long place;
        private Kingdom kingdom;
    }
}