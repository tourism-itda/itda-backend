package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;
import java.util.List;

/** No.27 응답: 저장 일정 상세 (v2). */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ItineraryDetailResponse(
        Long itineraryId,
        String title,
        Long contentId,
        String contentTitle,
        LocalDate travelDate,
        String region,
        String durationLabel,
        boolean isShared,
        List<ItineraryPlaceView> places
) {
}
