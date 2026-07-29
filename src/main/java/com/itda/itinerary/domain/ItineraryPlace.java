package com.itda.itinerary.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "itinerary_place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItineraryPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerary_place_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "day_number")
    private int dayNumber;

    @Column(name = "visit_order")
    private int visitOrder;

    @Enumerated(EnumType.STRING)
    private ItineraryPlaceStatus status;

    private String memo;

    @Builder
    private ItineraryPlace(Long placeId, int dayNumber, int visitOrder,
                           ItineraryPlaceStatus status, String memo) {
        this.placeId = placeId;
        this.dayNumber = dayNumber;
        this.visitOrder = visitOrder;
        this.status = status != null ? status : ItineraryPlaceStatus.PENDING;
        this.memo = memo;
    }

    void assignItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
}
