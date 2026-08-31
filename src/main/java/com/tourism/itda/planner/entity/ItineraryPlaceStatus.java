package com.tourism.itda.planner.entity;

/**
 * 일정 내 장소 상태.
 * PENDING   : 추천됨/미확정
 * CONFIRMED : 사용자가 확정
 * CHANGED   : '다른 곳 추천'으로 교체됨
 */
public enum ItineraryPlaceStatus {
    PENDING, CONFIRMED, CHANGED
}
