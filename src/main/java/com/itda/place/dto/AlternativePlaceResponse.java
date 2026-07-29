package com.itda.place.dto;

import com.itda.place.domain.Place;

/**
 * No.23 GET /places/alternative 응답 (v2) — '다른 곳 추천' 1건.
 * v2 형태: { place: { place_id, name, category, description, image_url, opening_hours, latitude, longitude } }
 */
public record AlternativePlaceResponse(AlternativePlace place) {

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
