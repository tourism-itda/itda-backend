package com.itda.itinerary.route;

/**
 * 촬영지 점수를 매길 때 필요한 주변 정보.
 *
 * @param totalSpots       콘텐츠에 매핑된 전체 촬영지 수 — recommend_order 정규화에 쓴다
 * @param targetSpotCount  최종적으로 배치할 촬영지 수 — 마지막 칸 도착 시각을 추정하는 데 쓴다
 * @param allowanceMeters  허용거리(m)
 */
public record ScoringContext(int totalSpots, int targetSpotCount, long allowanceMeters) {
}
