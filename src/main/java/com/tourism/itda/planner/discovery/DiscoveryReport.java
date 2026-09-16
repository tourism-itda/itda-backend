package com.tourism.itda.planner.discovery;

import java.util.List;

/**
 * {@link AnchorDiscoveryBatch} 실행 결과 집계.
 *
 * @param processedContents    이번 실행에서 시도한 작품 수
 * @param contentsWithAnchors  작품 관련 명소를 1곳 이상 확보한 작품 수
 * @param contentsWithZero     끝까지 0곳으로 남은 작품 수
 * @param placesCreated        새로 만든 place 행 수 (이미 있는 external_id/kakaoPlaceId 는 재사용하므로 제외)
 * @param mappingsCreated      새로 만든 content_place 매핑 수
 * @param tourApiVerifiedCount TourAPI 로 검증되어 채택된 앵커 수 — 관광공사 데이터 활용 비중 확인용
 * @param kakaoVerifiedCount   카카오로 검증되어 채택된 앵커 수 (TourAPI 가 0건일 때만 보완으로 쓰인다)
 * @param zeroAnchorContents   0곳으로 끝난 작품의 (content_id, title) 목록 — 후속 조치 대상 확인용
 */
public record DiscoveryReport(
        int processedContents,
        int contentsWithAnchors,
        int contentsWithZero,
        int placesCreated,
        int mappingsCreated,
        int tourApiVerifiedCount,
        int kakaoVerifiedCount,
        List<ZeroAnchorContent> zeroAnchorContents) {

    public record ZeroAnchorContent(Long contentId, String title) {
    }
}
