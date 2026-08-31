package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.tourism.itda.place.entity.PlaceType;

import java.time.LocalTime;

/**
 * 루트의 한 칸.
 *
 * @param visitOrder    0부터 시작하는 방문 순서
 * @param slotType      SPOT / RESTAURANT / CAFE
 * @param label         "촬영지" / "점심" / "카페" / "저녁"
 * @param estimatedTime 예상 도착 시각. 순서를 정하는 근거가 아니라 표시·검증용이다.
 * @param place         비어 있으면 null — 사용자가 후보에서 골라 채울 자리
 * @param filledBy      이 칸이 어떻게 채워졌는지
 * @param reason        Claude 가 붙인 선택 이유 (촬영지 자동 선택 시에만)
 * @param segmentIndex  비어 있는 칸이 속한 구간 번호. 후보 조회 API 에 그대로 넘기면 된다.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RouteSlotView(
        int visitOrder,
        PlaceType slotType,
        String label,
        LocalTime estimatedTime,
        RoutePlaceView place,
        SlotFilledBy filledBy,
        String reason,
        Integer segmentIndex) {

    public static RouteSlotView filled(int visitOrder, PlaceType type, String label, LocalTime time,
                                       RoutePlaceView place, SlotFilledBy filledBy, String reason) {
        return new RouteSlotView(visitOrder, type, label, time, place, filledBy, reason, null);
    }

    public static RouteSlotView empty(int visitOrder, PlaceType type, String label, LocalTime time,
                                      int segmentIndex) {
        return new RouteSlotView(visitOrder, type, label, time, null, SlotFilledBy.EMPTY, null, segmentIndex);
    }
}
