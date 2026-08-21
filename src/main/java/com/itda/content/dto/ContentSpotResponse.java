package com.itda.content.dto;

import com.itda.itinerary.route.ContentSpot;
import com.itda.place.domain.Place;

/**
 * 직접선택 화면에 뿌릴 촬영지 하나.
 *
 * @param recommendOrder 콘텐츠 안에서의 추천 순위. 사용자가 안 고른 자리를 서버가 채울 때 쓰인다.
 * @param nightOpen      저녁 이후에도 방문 가능한가. 하루 마지막 촬영지 자리에 중요하다.
 */
public record ContentSpotResponse(
        Long placeId,
        String name,
        String category,
        String description,
        String imageUrl,
        String openingHours,
        boolean nightOpen,
        double latitude,
        double longitude,
        int recommendOrder) {

    public static ContentSpotResponse of(ContentSpot spot, String imageUrl) {
        Place place = spot.place();
        return new ContentSpotResponse(
                place.getId(),
                place.getName(),
                place.getCategory(),
                place.getDescription(),
                imageUrl,
                place.getOpeningHours(),
                place.isNightOpen(),
                place.getLatitude(),
                place.getLongitude(),
                spot.recommendOrder());
    }
}
