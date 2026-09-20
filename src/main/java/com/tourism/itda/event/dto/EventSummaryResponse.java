package com.tourism.itda.event.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;

/** GET /api/events/upcoming 목록 항목. */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record EventSummaryResponse(
        String contentId,
        String title,
        String imageUrl,
        String address,
        LocalDate eventStartDate,
        LocalDate eventEndDate,
        String eventHomepage,
        // 관광API mapx/mapy. 좌표가 없는 행사도 있어 null 이면 응답에서 생략된다(NON_NULL).
        Double longitude,
        Double latitude) {
}
