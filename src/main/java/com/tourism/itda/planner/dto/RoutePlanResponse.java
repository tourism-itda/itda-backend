package com.tourism.itda.planner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tourism.itda.planner.route.ContentAnchorResolver;

import java.util.List;

/**
 * 하루 루트 미리보기. DB 에 아무것도 쓰지 않는다 — 사용자가 저장(No.28)을 눌러야 저장된다.
 *
 * <p>식당·카페 칸은 비어 있고, 사용자가 {@code segments} 의 타원 안에서 직접 고른다.
 *
 * @param spotCount 실제로 배치된 촬영지 수. 요청보다 적을 수 있다
 *                  (콘텐츠에 매핑된 촬영지가 부족한 경우).
 * @param anchorSource 작품 관련 명소를 어디서 얻었는가. {@code CONTENT_PLACE} 는 승인된 매핑,
 *                     {@code PERSON_CHAIN} 은 작품 인물의 연고지다. 후자는 촬영지가 아니므로
 *                     프론트에서 "작품 속 인물과 연결된 곳"으로 표기해야 한다.
 * @param travelDistanceMeters 명소 사이 총 도로거리(m). 카카오 길찾기로 실측하지 못했으면 null —
 *                             직선거리를 도로거리인 것처럼 보여주지 않는다.
 * @param travelDurationSeconds 명소 사이 총 이동시간(초). 이동시간은 명소 사이 차량 이동만이다.
 *                              관람·주차·식사 시간은 포함하지 않는다. 미검증이면 null.
 * @param roadVerified 카카오 길찾기로 실측했는가. false 면 위 두 값이 직선거리 기반 추정이거나
 *                     아예 비어 있을 수 있다.
 * @param withinTimeLimits 구간 40분·합계 80분 제한을 통과했는가. false 라도 순서 자체는 쓴다 —
 *                        하루 코스로 빡빡하다는 신호로 프론트가 안내에 쓸 수 있다.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoutePlanResponse(
        Long contentId,
        String contentTitle,
        String region,
        int spotCount,
        long allowanceMeters,
        ContentAnchorResolver.AnchorSource anchorSource,
        List<RouteSlotView> slots,
        List<RouteSegmentView> segments,
        Long travelDistanceMeters,
        Long travelDurationSeconds,
        boolean roadVerified,
        boolean withinTimeLimits) {
}
