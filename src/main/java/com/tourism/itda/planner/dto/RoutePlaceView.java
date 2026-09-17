package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceType;
import com.tourism.itda.place.service.PlaceholderImages;

/**
 * 루트의 한 칸에 들어간 장소. 지도 핀과 카드에 필요한 최소 정보만 담는다.
 *
 * @param imageUrl           대표 사진. 구하지 못한 장소는 분류에 맞는 기본 이미지가 들어온다
 *                           (비어 있지 않다).
 * @param imageIsPlaceholder 위 값이 실제 사진이 아니라 기본 이미지인가.
 *                           true 면 "사진 있음" 취급을 하면 안 된다.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoutePlaceView(
        Long placeId,
        PlaceType placeType,
        String name,
        String category,
        String address,
        String imageUrl,
        boolean imageIsPlaceholder,
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
                PlaceholderImages.isPlaceholder(imageUrl),
                place.getOpeningHours(),
                place.isNightOpen(),
                place.getLatitude(),
                place.getLongitude());
    }
}
