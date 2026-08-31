package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceType;

/** 루트의 한 칸에 들어간 장소. 지도 핀과 카드에 필요한 최소 정보만 담는다. */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoutePlaceView(
        Long placeId,
        PlaceType placeType,
        String name,
        String category,
        String address,
        String imageUrl,
        String openingHours,
        boolean nightOpen,
        double latitude,
        double longitude) {

    public static RoutePlaceView of(Place place, String imageUrl) {
        return new RoutePlaceView(
                place.getId(),
                place.getPlaceType(),
                place.getName(),
                place.getCategory(),
                place.getAddress(),
                imageUrl,
                place.getOpeningHours(),
                place.isNightOpen(),
                place.getLatitude(),
                place.getLongitude());
    }
}
