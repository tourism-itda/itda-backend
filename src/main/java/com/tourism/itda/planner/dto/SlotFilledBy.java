package com.tourism.itda.planner.dto;

/** 이 슬롯의 장소가 어떻게 정해졌는지. 프론트에서 뱃지로 구분해 보여줄 수 있다. */
public enum SlotFilledBy {

    /** 사용자가 직접 고른 촬영지. */
    USER,

    /** 코드 점수 + Claude 큐레이션으로 채운 촬영지. */
    CURATED,

    /** 코드 점수만으로 채운 촬영지 (Claude 미사용 또는 폴백). */
    SCORED,

    /**
     * 작품 관련 명소가 아니라 주변 일반 관광명소로 채운 칸. 프론트에서 작품 관련
     * 문구를 붙이면 안 된다.
     */
    GENERAL,

    /** 아직 비어 있음 — 사용자가 후보에서 고를 자리. */
    EMPTY
}
