package com.itda.itinerary.route;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalTime;

/**
 * 하루 일정 생성에 쓰는 조정값들. 전부 application.yml 에서 바꿀 수 있다.
 *
 * <p>기본값은 팀 합의 전 잠정치다 — 체류시간은 실제 사용해 보고 조정할 것.
 *
 * @param dayStart               하루 시작 시각. 첫 촬영지 도착 시각.
 * @param spotDwellMinutes       촬영지 체류시간(분)
 * @param restaurantDwellMinutes 식당 체류시간(분)
 * @param cafeDwellMinutes       카페 체류시간(분)
 * @param nightThreshold         <b>장소의 성질</b>: 이 시각 이후에도 열려 있으면 {@code night_open}.
 *                               영업시간 원문을 파싱할 때 쓰는 기준이다.
 * @param lateArrivalThreshold   <b>일정의 상황</b>: 마지막 칸 도착 예상이 이 시각 이후면
 *                               "문 닫은 곳이 많을 시간"으로 보고 {@code night_open} 장소를 우대한다.
 *                               국내 관광지는 대개 18시에 닫으므로 야간 기준(20시)보다 이르다.
 * @param defaultAllowanceMeters 사용자가 허용거리를 안 보냈을 때 쓰는 기본값(m)
 * @param maxAllowanceMeters     허용거리 상한(m). 너무 크면 동선이 무의미해진다.
 * @param candidateLimit         지도에 뿌릴 구간별 후보 개수
 * @param curatorShortlistSize   LLM 에게 넘길 촬영지 후보 개수 (코드가 이만큼으로 좁힌다)
 * @param detourWeight           촬영지 점수 중 동선(우회거리) 비중
 * @param orderWeight            촬영지 점수 중 recommend_order 비중
 * @param nightWeight            촬영지 점수 중 야간 운영 비중 (마지막 슬롯에만 적용)
 */
@ConfigurationProperties(prefix = "itda.route")
public record RouteProperties(
        LocalTime dayStart,
        int spotDwellMinutes,
        int restaurantDwellMinutes,
        int cafeDwellMinutes,
        LocalTime nightThreshold,
        LocalTime lateArrivalThreshold,
        long defaultAllowanceMeters,
        long maxAllowanceMeters,
        int candidateLimit,
        int curatorShortlistSize,
        double detourWeight,
        double orderWeight,
        double nightWeight) {

    public RouteProperties {
        if (dayStart == null) {
            dayStart = LocalTime.of(10, 0);
        }
        if (spotDwellMinutes <= 0) {
            spotDwellMinutes = 60;
        }
        if (restaurantDwellMinutes <= 0) {
            restaurantDwellMinutes = 60;
        }
        if (cafeDwellMinutes <= 0) {
            cafeDwellMinutes = 40;
        }
        if (nightThreshold == null) {
            nightThreshold = LocalTime.of(20, 0);
        }
        if (lateArrivalThreshold == null) {
            lateArrivalThreshold = LocalTime.of(18, 0);
        }
        if (defaultAllowanceMeters <= 0) {
            defaultAllowanceMeters = 3_000L;
        }
        if (maxAllowanceMeters <= 0) {
            maxAllowanceMeters = 20_000L;
        }
        if (candidateLimit <= 0) {
            candidateLimit = 8;
        }
        if (curatorShortlistSize <= 0) {
            curatorShortlistSize = 5;
        }
        if (detourWeight <= 0 && orderWeight <= 0 && nightWeight <= 0) {
            detourWeight = 0.6;
            orderWeight = 0.3;
            nightWeight = 0.1;
        }
    }

    /** 사용자가 보낸 허용거리를 유효 범위로 자른다. null 이면 기본값. */
    public long clampAllowance(Long requested) {
        if (requested == null) {
            return defaultAllowanceMeters;
        }
        return Math.max(0L, Math.min(maxAllowanceMeters, requested));
    }

    public int dwellMinutesOf(com.itda.place.domain.PlaceType type) {
        return switch (type) {
            case SPOT -> spotDwellMinutes;
            case RESTAURANT -> restaurantDwellMinutes;
            case CAFE -> cafeDwellMinutes;
        };
    }
}
