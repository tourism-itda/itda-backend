package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tourism.itda.place.entity.Place;

import java.util.List;

/**
 * GET /api/places/{placeId} 응답.
 * fee(입장료) 필드 없음 — v2 제거 확정. is_bookmarked 는 비로그인이면 항상 false.
 */
public record PlaceDetailResponse(
        @JsonProperty("place_id") Long placeId,
        String name,
        String category,
        String description,
        double latitude,
        double longitude,
        String address,
        String region,
        @JsonProperty("opening_hours") String openingHours,
        @JsonProperty("kakao_place_id") String kakaoPlaceId,
        List<PlaceImageResponse> images,
        @JsonProperty("is_bookmarked") boolean isBookmarked
) {
    public static PlaceDetailResponse of(Place place, List<PlaceImageResponse> images, boolean isBookmarked) {
        return new PlaceDetailResponse(
                place.getId(), place.getName(), place.getCategory(), place.getDescription(),
                place.getLatitude(), place.getLongitude(), place.getAddress(), place.getRegion(),
                place.getOpeningHours(), place.getKakaoPlaceId(), images, isBookmarked);
    }
}
