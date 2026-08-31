package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.tourism.itda.place.entity.Place;

import java.util.List;

/**
 * No.25 GET /places/:place_id 응답 (v4). 인증 선택.
 * ⚠️ fee(입장료) 제거 확정.
 * is_bookmarked: 로그인 시에만 계산(비로그인 false). v3부터 북마크는 장소 전용.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PlaceDetailResponse(
        Long placeId,
        String name,
        String category,
        String description,
        double latitude,
        double longitude,
        String address,
        String region,
        String openingHours,
        String kakaoPlaceId,
        List<PlaceImageDto> images,
        boolean isBookmarked
) {
    public static PlaceDetailResponse of(Place place, List<PlaceImageDto> images, boolean isBookmarked) {
        return new PlaceDetailResponse(
                place.getId(),
                place.getName(),
                place.getCategory(),
                place.getDescription(),
                place.getLatitude(),
                place.getLongitude(),
                place.getAddress(),
                place.getRegion(),
                place.getOpeningHours(),
                place.getKakaoPlaceId(),
                images,
                isBookmarked
        );
    }
}
