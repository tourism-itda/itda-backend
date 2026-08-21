package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * No.24 추천 일정의 slot (v2). place 로 감싸고 to_next_* 는 place 내부.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RecommendSlot(
        int visitOrder,
        RecommendPlace place
) {
    // 중첩 record 에는 바깥의 @JsonNaming 이 상속되지 않으므로 여기에도 붙인다.
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
