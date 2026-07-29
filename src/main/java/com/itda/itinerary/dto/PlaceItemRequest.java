package com.itda.itinerary.dto;

import jakarta.validation.constraints.NotNull;

/**
 * POST/PATCH 일정의 places[] 항목.
 * status 는 문자열로 받아 서비스에서 enum(PENDING|CONFIRMED|CHANGED) 로 변환.
 */
public record PlaceItemRequest(
        @NotNull Long placeId,
        Integer dayNumber,       // null 이면 1일차로 처리
        @NotNull Integer visitOrder,
        String status,           // null 이면 PENDING
        String memo
) {
}
