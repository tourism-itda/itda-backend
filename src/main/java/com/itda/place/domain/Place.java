package com.itda.place.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    private String name;
    private String category;

    @Column(columnDefinition = "text")
    private String description;

    private double latitude;
    private double longitude;

    private String address;
    private String region;

    @Column(name = "opening_hours")
    private String openingHours;

    @Column(name = "kakao_place_id")
    private String kakaoPlaceId;
}
