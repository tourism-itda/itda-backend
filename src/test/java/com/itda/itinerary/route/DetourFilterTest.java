package com.itda.itinerary.route;

import com.itda.common.distance.Coord;
import com.itda.common.distance.HaversineDistanceCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DetourFilterTest {

    /** 위도만 다른 두 점 — 정남북 직선이라 우회거리 계산을 눈으로 검증하기 쉽다. */
    private static final Coord A = new Coord(37.00, 127.00);
    private static final Coord B = new Coord(37.10, 127.00);   // A 에서 북쪽으로 약 11.1km

    private DetourFilter filter;

    @BeforeEach
    void setUp() {
        filter = new DetourFilter(new HaversineDistanceCalculator(25));
    }

    @Test
    @DisplayName("A→B 직선 위의 점은 우회거리가 사실상 0이다")
    void pointOnTheLineHasNoDetour() {
        Coord onLine = new Coord(37.05, 127.00);

        // 거리 계산이 미터 단위 long 으로 반올림되므로 세 번 더하면 1~2m 오차가 남는다.
        // 허용거리는 수백~수천 m 단위라 이 정도 오차는 판정에 영향을 주지 않는다.
        assertThat(filter.detourMeters(A, onLine, B)).isLessThanOrEqualTo(2);
    }

    @Test
    @DisplayName("옆으로 샌 점은 우회거리가 생기고, 멀수록 커진다")
    void detourGrowsWithSidewaysDistance() {
        Coord slightlyOff = new Coord(37.05, 127.01);
        Coord farOff = new Coord(37.05, 127.05);

        long near = filter.detourMeters(A, slightlyOff, B);
        long far = filter.detourMeters(A, farOff, B);

        assertThat(near).isPositive();
        assertThat(far).isGreaterThan(near);
    }

    @Test
    @DisplayName("촬영지 바로 옆 가게는 우회거리가 거의 0 — 중간점 반경 방식이 놓치던 지점")
    void placeRightNextToAnAnchorIsNearlyFree() {
        // A 에서 동쪽으로 약 90m. 중간점 기준 원이었다면 반경에서 한참 벗어난다.
        Coord besideAnchor = new Coord(37.00, 127.001);

        long detour = filter.detourMeters(A, besideAnchor, B);

        assertThat(detour).isLessThan(300);
        assertThat(filter.withinEllipse(A, besideAnchor, B, 1_000)).isTrue();
    }

    @Test
    @DisplayName("허용거리가 타원의 두께를 정한다")
    void allowanceDecidesMembership() {
        Coord offRoute = new Coord(37.05, 127.02);
        long detour = filter.detourMeters(A, offRoute, B);

        assertThat(filter.withinEllipse(A, offRoute, B, detour)).isTrue();
        assertThat(filter.withinEllipse(A, offRoute, B, detour - 1)).isFalse();
        assertThat(filter.withinEllipse(A, offRoute, B, detour + 1_000)).isTrue();
    }

    @Test
    @DisplayName("검색 원 하나가 타원을 완전히 덮는다")
    void searchCircleCoversTheEllipse() {
        long allowance = 3_000;
        List<SearchArea> areas = filter.searchAreas(A, B, allowance);

        assertThat(areas).hasSize(1);
        SearchArea area = areas.get(0);

        // 장반경 = (초점간 거리 + 허용거리) / 2
        long expectedRadius = (filter.distance(A, B) + allowance) / 2;
        assertThat(area.radiusMeters()).isEqualTo(expectedRadius);

        // 타원 경계에 있는 점(우회거리 == 허용거리)이 원 안에 들어와야 검색에서 안 빠진다.
        assertThat(filter.distance(area.center(), A)).isLessThanOrEqualTo(area.radiusMeters());
        assertThat(filter.distance(area.center(), B)).isLessThanOrEqualTo(area.radiusMeters());
    }

    @Test
    @DisplayName("두 촬영지가 아주 가까워도 검색 반경이 0으로 붕괴하지 않는다")
    void radiusHasFloor() {
        Coord almostSame = new Coord(37.00001, 127.00001);

        List<SearchArea> areas = filter.searchAreas(A, almostSame, 0);

        assertThat(areas).hasSize(1);
        assertThat(areas.get(0).radiusMeters()).isEqualTo(DetourFilter.MIN_SEARCH_RADIUS_M);
    }

    @Test
    @DisplayName("40km 를 넘으면 관광API radius 상한 때문에 앵커 주변 두 원으로 쪼갠다")
    void veryLongSegmentFallsBackToTwoCircles() {
        Coord farAway = new Coord(37.60, 127.00);   // A 에서 약 66km

        assertThat(filter.isPartialCoverage(A, farAway, 3_000)).isTrue();

        List<SearchArea> areas = filter.searchAreas(A, farAway, 3_000);
        assertThat(areas).hasSize(2);
        assertThat(areas).allSatisfy(area ->
                assertThat(area.radiusMeters()).isLessThanOrEqualTo(DetourFilter.MAX_SEARCH_RADIUS_M));
    }

    @Test
    @DisplayName("검색 반경은 항상 관광API 상한(20km) 이하다")
    void radiusNeverExceedsTourApiLimit() {
        List<SearchArea> areas = filter.searchAreas(A, new Coord(37.30, 127.00), 20_000);

        assertThat(areas).allSatisfy(area ->
                assertThat(area.radiusMeters()).isLessThanOrEqualTo(DetourFilter.MAX_SEARCH_RADIUS_M));
    }

    @Test
    @DisplayName("꼬리 구간은 앵커 주변 원 하나로 검색한다")
    void trailingSegmentSearchesAroundTheAnchor() {
        SearchArea area = filter.trailingSearchArea(A, 2_500);

        assertThat(area.center()).isEqualTo(A);
        assertThat(area.radiusMeters()).isEqualTo(2_500);
    }
}
