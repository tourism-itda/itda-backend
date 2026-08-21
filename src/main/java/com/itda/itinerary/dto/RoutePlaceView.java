package com.itda.itinerary.dto;

import com.itda.place.domain.Place;
import com.itda.place.domain.PlaceType;

/** 루트의 한 칸에 들어간 장소. 지도 핀과 카드에 필요한 최소 정보만 담는다. */
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
