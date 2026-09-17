package com.tourism.itda.place.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

/**
 * 장소 사진 현황. 백필을 돌리기 전에 "지금 몇 곳이 비어 있는지"를 먼저 보려고 만든 것이다.
 *
 * <p>외부 API 를 부르지 않고 DB 만 센다.
 *
 * @param totalPlaces        place 행 전체 수
 * @param withImage          대표 이미지가 있는 장소 수
 * @param withoutImage       대표 이미지가 없는 장소 수 — 기본 이미지로 나가는 곳
 * @param withoutDescription 설명이 비어 있는 장소 수 — 백필 대상
 * @param bySource           출처별 내역. 관광공사 데이터 비중과 카카오 보완 비중을 같이 볼 수 있다.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PlaceImageStatusResponse(
        long totalPlaces,
        long withImage,
        long withoutImage,
        long withoutDescription,
        List<SourceStatus> bySource) {

    /**
     * @param source       SEED / TOUR_API / KAKAO
     * @param total        그 출처의 장소 수
     * @param withImage    그중 대표 이미지가 있는 수
     * @param withoutImage 그중 비어 있는 수
     */
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record SourceStatus(
            String source,
            long total,
            long withImage,
            long withoutImage) {
    }
}
