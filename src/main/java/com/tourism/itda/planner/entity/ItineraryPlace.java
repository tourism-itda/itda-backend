package com.tourism.itda.planner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** 저장된 일정 안의 장소 한 칸. itinerary 는 같은 planner 도메인 소유라 실제 연관관계로 건다. */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItineraryPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;

    /** place 패키지 소유라 plain Long — 다른 도메인 간 결합 방지. */
    private Long placeId;

    private Integer dayNumber;
    private int visitOrder;

    @Enumerated(EnumType.STRING)
    private ItineraryPlaceStatus status;

    private String memo;

    public ItineraryPlace(Long placeId, Integer dayNumber, int visitOrder,
                           ItineraryPlaceStatus status, String memo) {
        this.placeId = placeId;
        this.dayNumber = dayNumber != null ? dayNumber : 1;
        this.visitOrder = visitOrder;
        this.status = status != null ? status : ItineraryPlaceStatus.PENDING;
        this.memo = memo;
    }

    void assignTo(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
}
