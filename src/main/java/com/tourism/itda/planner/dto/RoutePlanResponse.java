package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * 하루 루트 미리보기. DB 에 아무것도 쓰지 않는다 — 사용자가 저장(No.28)을 눌러야 저장된다.
 *
 * <p>식당·카페 칸은 비어 있고, 사용자가 {@code segments} 의 타원 안에서 직접 고른다.
 *
 * @param spotCount 실제로 배치된 촬영지 수. 요청보다 적을 수 있다
 *                  (콘텐츠에 매핑된 촬영지가 부족한 경우).
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoutePlanResponse(
        Long contentId,
        String contentTitle,
        String region,
        int spotCount,
        long allowanceMeters,
        List<RouteSlotView> slots,
        List<RouteSegmentView> segments) {
}
