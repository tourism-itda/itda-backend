package com.itda.itinerary.dto;

/**
 * No.24 추천 일정의 slot (v2). place 로 감싸고 to_next_* 는 place 내부.
 */
public record RecommendSlot(
        int visitOrder,
        RecommendPlace place
) {
    public record RecommendPlace(
            Long placeId,
            String name,
            String category,
            String description,
            String imageUrl,
            String openingHours,
            Long toNextDistanceM,
            Long toNextDurationMin,
            double latitude,
            double longitude
    ) {
    }
}
