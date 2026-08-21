package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotNull;

/**
 * POST/PATCH 일정의 places[] 항목.
 * status 는 문자열로 받아 서비스에서 enum(PENDING|CONFIRMED|CHANGED) 로 변환.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PlaceItemRequest(
        @NotNull Long placeId,
        Integer dayNumber,       // null 이면 1일차로 처리
        @NotNull Integer visitOrder,
        String status,           // null 이면 PENDING
        String memo
) {
}
