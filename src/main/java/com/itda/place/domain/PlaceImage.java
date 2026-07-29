package com.itda.place.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "place_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_image_id")
    private Long id;

    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_primary")
    private boolean primary;

    @Column(name = "sort_order")
    private int sortOrder;
}
