package com.itda.common.distance;

/**
 * 두 좌표 간 거리/소요시간 계산 추상화.
 * 지금은 하버사인 직선거리 구현이지만, 추후 카카오 길찾기 API 구현체로 교체 가능. (TODO 1번 결정)
 */
public interface DistanceCalculator {

    /** 두 좌표 사이 거리(미터). */
    long distanceMeters(double lat1, double lon1, double lat2, double lon2);

    /** 두 좌표 사이 예상 소요시간(분). */
    long durationMinutes(double lat1, double lon1, double lat2, double lon2);
}
