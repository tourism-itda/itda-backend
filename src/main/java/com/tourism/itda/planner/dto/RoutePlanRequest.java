package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 하루 루트 생성 요청.
 *
 * <p>두 가지 모드를 하나의 요청으로 처리한다:
 * <ul>
 *   <li><b>자동추천</b> — {@code spotPlaceIds} 를 비워 보내면 서버가 3곳을 고른다</li>
 *   <li><b>직접선택</b> — 사용자가 고른 촬영지를 최대 3개까지 보낸다.
 *       3개보다 적으면 나머지를 서버가 채운다</li>
 * </ul>
 *
 * @param spotPlaceIds    사용자가 "꼭 가고 싶다"고 고른 촬영지. 최대 3개.
 * @param excludePlaceIds 이번 추천에서 빼고 싶은 place_id. "다른 코스 보기"(재생성)에 쓴다.
 *                        직전에 받은 루트의 명소 place_id 를 그대로 넘기면 다른 조합이 나온다.
 *                        작품 관련 명소가 하나도 안 남게 되는 경우에는 이 제외를 무시한다 —
 *                        관련 명소 0곳짜리 루트를 만들지 않는 것이 더 중요하기 때문이다.
 *                        {@code spotPlaceIds} 로 명시한 곳에는 적용하지 않는다.
 * @param allowanceMeters 동선이 몇 m 늘어나도 되는지. 타원의 두께를 정한다.
 *                        생략하면 서버 기본값(3km).
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoutePlanRequest(
        @NotNull Long contentId,
        List<Long> spotPlaceIds,
        List<Long> excludePlaceIds,
        Long allowanceMeters) {

    public List<Long> spotPlaceIdsOrEmpty() {
        return spotPlaceIds == null ? List.of() : spotPlaceIds;
    }

    public List<Long> excludePlaceIdsOrEmpty() {
        return excludePlaceIds == null ? List.of() : excludePlaceIds;
    }
}
