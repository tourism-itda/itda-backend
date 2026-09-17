package com.tourism.itda.place.entity;

/**
 * place 레코드의 출처.
 *
 * <p>TOUR_API 로 들어온 장소는 (source, external_id) 유니크 제약으로 중복 없이 재사용된다.
 * external_id 는 관광API 의 contentid.
 */
public enum PlaceSource {

    /** data.sql 시드 데이터 (촬영지). */
    SEED,

    /** 한국관광공사 TourAPI 에서 온디맨드로 가져와 저장한 장소. */
    TOUR_API,

    /**
     * 카카오 로컬 API 에서 온디맨드로 가져와 저장한 장소.
     *
     * <p>TourAPI 가 1순위이고 카카오는 TourAPI 로 후보 수를 못 채울 때만 보완재로 쓴다
     * (공모전 평가 기준이 관광공사 데이터 활용도라 TourAPI 비중을 우선한다).
     */
    KAKAO
}
