package com.tourism.itda.place.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.tourism.itda.place.entity.Place;

/**
 * No.23 GET /places/alternative 응답 (v2) — '다른 곳 추천' 1건.
 * v2 형태: { place: { place_id, name, category, description, image_url, opening_hours, latitude, longitude } }
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AlternativePlaceResponse(AlternativePlace place) {

    // 중첩 record 에는 바깥의 @JsonNaming 이 상속되지 않으므로 여기에도 붙인다.
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record AlternativePlace(
            Long placeId,
            String name,
            String category,
            String description,
            String imageUrl,
            String openingHours,
            double latitude,
            double longitude
    ) {
    }

    public static AlternativePlaceResponse of(Place place, String imageUrl) {
        return new AlternativePlaceResponse(new AlternativePlace(
                place.getId(),
                place.getName(),
                place.getCategory(),
                place.getDescription(),
                imageUrl,
                place.getOpeningHours(),
                place.getLatitude(),
                place.getLongitude()
        ));
    }
}
