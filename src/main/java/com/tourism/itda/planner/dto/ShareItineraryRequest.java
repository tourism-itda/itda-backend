package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * No.38 POST /itineraries/:id/share 요청 바디. 모든 필드 optional.
 * ⚠️ description: Itinerary 엔티티에 대응하는 컬럼이 없어 현재는 무시된다.
 * tags 가 전달되면 해당 일정의 기존 태그 전체를 교체한다(place 전체 교체와 동일한 컨벤션).
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ShareItineraryRequest(
        String description,
        String region,
        List<String> tags
) {
}
