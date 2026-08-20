package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.place.entity.Place;

/**
 * fee(입장료) 필드 없음 — v2에서 제거 확정.
 * to_next_* 는 다음 슬롯이 없으면(마지막 장소) null 로 내려가고 키 자체가 빠진다.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RecommendPlaceResponse(
        @JsonProperty("place_id") Long placeId,
        String name,
        String category,
        String description,
        @JsonProperty("image_url") String imageUrl,
        @JsonProperty("opening_hours") String openingHours,
        @JsonProperty("to_next_distance_m") Long toNextDistanceM,
        @JsonProperty("to_next_duration_min") Long toNextDurationMin,
        double latitude,
        double longitude
) {
    public static RecommendPlaceResponse of(Place place, String imageUrl, Long toNextDistanceM, Long toNextDurationMin) {
        return new RecommendPlaceResponse(
                place.getId(), place.getName(), place.getCategory(), place.getDescription(),
                imageUrl, place.getOpeningHours(),
                toNextDistanceM, toNextDurationMin,
                place.getLatitude(), place.getLongitude());
    }
}
