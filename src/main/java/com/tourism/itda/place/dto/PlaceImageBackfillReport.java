package com.tourism.itda.place.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * 장소 사진 백필 배치 실행 결과.
 *
 * @param processed          대표 이미지가 없어 이번에 시도한 장소 수
 * @param filledByContentId  관광API contentId 로 {@code firstimage} 를 바로 받은 수 (가장 믿을 만한 경로)
 * @param filledByNameMatch  contentId 가 없어 이름+좌표로 관광API 를 되짚어 찾은 수
 * @param filledByNaver      관광API 에 없어 네이버 이미지 검색으로 채운 수
 * @param stillMissing       끝내 못 채운 수
 * @param missingPlaces      못 채운 장소 목록 — 수동 보정 대상 확인용
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PlaceImageBackfillReport(
        int processed,
        int filledByContentId,
        int filledByNameMatch,
        int filledByNaver,
        int stillMissing,
        List<MissingPlace> missingPlaces) {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record MissingPlace(Long placeId, String name, String source) {
    }
}
