package com.tourism.itda.global.distance;

/** 위경도 한 쌍. Place / 관광API 응답 / 계산 중간값을 같은 타입으로 다루기 위한 값 객체. */
public record Coord(double latitude, double longitude) {

    /**
     * 두 좌표의 중간점.
     *
     * <p>구면 중점이 아니라 산술 평균이다. 국내 위도대에서 40km 이하 구간이면
     * 오차가 미터 단위라 후보 검색용 원의 중심으로 쓰기에 충분하다.
     */
    public static Coord midpoint(Coord a, Coord b) {
        return new Coord((a.latitude + b.latitude) / 2, (a.longitude + b.longitude) / 2);
    }
}
