package com.itda.itinerary.dto;

import java.util.List;

/**
 * 촬영지와 촬영지 사이의 '채울 구간'. 지도에 그리는 타원 하나에 대응한다.
 *
 * <p>프론트는 이 정보로 타원을 그리고, 사용자가 허용거리 슬라이더를 움직이면
 * {@code allowanceMeters} 만 바꿔 후보 조회 API 를 다시 부르면 된다.
 *
 * @param segmentIndex     구간 번호. 후보 조회 API 의 파라미터.
 * @param startPlaceId     앞쪽 앵커 촬영지
 * @param endPlaceId       뒤쪽 앵커 촬영지. null 이면 하루 마지막이라 뒤 앵커가 없다.
 * @param startLatitude    앵커 좌표 — 타원의 초점
 * @param directDistanceM  두 앵커 사이 직선거리(m). 타원의 초점 간 거리.
 * @param allowanceMeters  허용거리(m). 이 값이 곧 타원의 '두께'를 정한다.
 * @param partialCoverage  두 촬영지가 너무 멀어(합계 40km 초과) 앵커 주변만 검색한 경우 true.
 *                         이때 후보는 구간 중앙부를 포함하지 못한다.
 * @param slotOrders       이 구간에서 채워야 할 슬롯들의 visit_order
 */
public record RouteSegmentView(
        int segmentIndex,
        Long startPlaceId,
        Long endPlaceId,
        double startLatitude,
        double startLongitude,
        Double endLatitude,
        Double endLongitude,
        long directDistanceM,
        long allowanceMeters,
        boolean partialCoverage,
        List<Integer> slotOrders) {
}
