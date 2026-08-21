package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.tourism.itda.place.entity.PlaceType;

import java.util.List;

/**
 * 한 구간의 후보 목록.
 *
 * @param allowanceMeters 실제 적용된 허용거리. 요청값이 상한을 넘으면 잘려서 여기 반영된다.
 * @param partialCoverage 두 촬영지가 너무 멀어 앵커 주변만 검색한 경우 true.
 *                        구간 중앙부의 가게는 후보에 없다는 뜻이므로 UI 에서 알려주는 게 좋다.
 * @param candidates      우회거리 오름차순. 비어 있을 수 있다 —
 *                        관광공사에 등록된 업소가 반경 안에 없는 지역이 실제로 있다.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record CandidateListResponse(
        PlaceType slotType,
        long allowanceMeters,
        boolean partialCoverage,
        List<CandidateView> candidates) {
}
