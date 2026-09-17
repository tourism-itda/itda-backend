package com.tourism.itda.place.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * 장소 사진·설명 백필 배치 실행 결과.
 *
 * @param processed              사진이나 설명이 비어 있어 이번에 시도한 장소 수
 * @param imagesFilledByContentId 관광API contentId 를 이미 들고 있어 바로 채운 수 (가장 믿을 만한 경로)
 * @param imagesFilledByNameMatch contentId 가 없어 좌표+이름으로 되짚어 찾아 채운 수
 * @param descriptionsFilled     {@code detailCommon2.overview} 로 설명을 채운 수
 * @param notFoundInTourApi      관광API 에서 못 찾은 수. <b>실패가 아니다</b> — 카카오로 보충한
 *                               식당은 애초에 관광API 에 없어서 카카오로 넘어온 곳들이고,
 *                               사진은 기본 이미지로 나간다.
 * @param missingPlaces          그 장소 목록 — 수동 보정 대상 확인용
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PlaceBackfillReport(
        int processed,
        int imagesFilledByContentId,
        int imagesFilledByNameMatch,
        int descriptionsFilled,
        int notFoundInTourApi,
        List<MissingPlace> missingPlaces) {

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record MissingPlace(Long placeId, String name, String source) {
    }
}
