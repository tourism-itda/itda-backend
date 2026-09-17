package com.tourism.itda.planner.route;

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
 * @param anchorClusterSpanMeters 앵커를 한 지역으로 묶는 반경(m). 이보다 멀리 떨어진 앵커는
 *                               같은 하루 코스에 넣지 않는다. 전국에 흩어진 인물 연고지를
 *                               억지로 잇는 것을 막는 장치다.
 * @param maxLegDurationSeconds   명소 사이 한 구간의 차량 소요시간 상한(초). 기본 40분.
 * @param maxTotalDurationSeconds 명소 사이 이동시간 합의 상한(초). 기본 80분.
 *                               관람·식사·주차 시간은 포함하지 않은 값이다.
 * @param orderVerifyAttempts     방문 순서 후보 중 길찾기 API 로 실제 검증할 개수.
 *                               순서 하나당 구간 수만큼 호출하므로 쿼터와 직결된다.
 * @param minSpotSeparationMeters 한 루트에 들어가는 명소끼리 최소한 떨어져 있어야 하는 거리(m).
 *                               수원 화성과 화성행궁은 460m 떨어진 사실상 같은 관광지인데
 *                               둘 다 뽑혀서 코스에 다양성이 없었다. 동선 점수만 보면
 *                               붙어 있는 곳이 항상 이기므로 별도 하한이 필요하다.
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
        double nightWeight,
        long anchorClusterSpanMeters,
        long maxLegDurationSeconds,
        long maxTotalDurationSeconds,
        int orderVerifyAttempts,
        long minSpotSeparationMeters) {

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
        if (anchorClusterSpanMeters <= 0) {
            anchorClusterSpanMeters = 25_000L;
        }
        if (maxLegDurationSeconds <= 0) {
            maxLegDurationSeconds = 2_400L;      // 40분
        }
        if (maxTotalDurationSeconds <= 0) {
            maxTotalDurationSeconds = 4_800L;    // 80분
        }
        if (orderVerifyAttempts <= 0) {
            orderVerifyAttempts = 3;
        }
        if (minSpotSeparationMeters <= 0) {
            minSpotSeparationMeters = 1_000L;
        }
    }

    /** 사용자가 보낸 허용거리를 유효 범위로 자른다. null 이면 기본값. */
    public long clampAllowance(Long requested) {
        if (requested == null) {
            return defaultAllowanceMeters;
        }
        return Math.max(0L, Math.min(maxAllowanceMeters, requested));
    }

    public int dwellMinutesOf(com.tourism.itda.place.entity.PlaceType type) {
        return switch (type) {
            case SPOT -> spotDwellMinutes;
            case RESTAURANT -> restaurantDwellMinutes;
            case CAFE -> cafeDwellMinutes;
        };
    }
}
