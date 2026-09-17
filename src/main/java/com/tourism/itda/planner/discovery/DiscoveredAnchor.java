package com.tourism.itda.planner.discovery;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.place.entity.PlaceSource;

/**
 * 검증까지 통과해 채택된 작품 관련 명소 1곳.
 *
 * <p>이 프로젝트는 한국관광공사 TourAPI 활용이 전제이므로, 검증은 TourAPI 를 1순위로
 * 시도하고 결과가 없을 때만 카카오 로컬 API 로 보완한다. {@link #source()} 로 어느 쪽에서
 * 검증됐는지 남겨 배치 집계·place 적재 방식(TOUR_API 는 external_id, KAKAO 는 kakaoPlaceId)을 가른다.
 *
 * @param source       TOUR_API 또는 KAKAO
 * @param externalId   TOUR_API 면 관광API contentid, KAKAO 면 카카오 place id
 * @param name         장소명
 * @param category     출처별 원문 카테고리 문자열 (TourAPI cat3 / 카카오 category_name)
 * @param address      주소
 * @param coord        좌표
 * @param relationType {@link DiscoveredPlace#relationType()} 그대로 — HISTORICAL / FILMING / EXHIBIT
 * @param reason       Claude 가 제시한 연결 근거
 * @param confidence   Claude 가 매긴 확신도(1~5)
 */
public record DiscoveredAnchor(
        PlaceSource source,
        String externalId,
        String name,
        String category,
        String address,
        Coord coord,
        String relationType,
        String reason,
        int confidence) {
}
