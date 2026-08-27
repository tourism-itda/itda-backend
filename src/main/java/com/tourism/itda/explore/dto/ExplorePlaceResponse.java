package com.tourism.itda.explore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.place.entity.Place;
import lombok.Getter;

@Getter
public class ExplorePlaceResponse {

    @JsonProperty("place_id")
    private final Long placeId;

    private final String name;
    private final String category;
    private final String description;
    private final double latitude;
    private final double longitude;
    private final String address;
    private final String region;

    public ExplorePlaceResponse(Place place) {
        this.placeId = place.getId();
        this.name = place.getName();
        this.category = place.getCategory();
        this.description = place.getDescription();
        this.latitude = place.getLatitude();
        this.longitude = place.getLongitude();
        this.address = place.getAddress();
        this.region = place.getRegion();
    }
}