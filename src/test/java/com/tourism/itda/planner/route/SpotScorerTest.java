package com.tourism.itda.planner.route;

import com.tourism.itda.global.distance.HaversineDistanceCalculator;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpotScorerTest {

    private static final long ALLOWANCE = 3_000L;

    @Test
    @DisplayName("동선을 적게 늘리는 촬영지가 위로 온다")
    void prefersSmallerDetour() {
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 120, 90, 60);

        ContentSpot anchor = spot(1L, 37.00, 127.00, 1, false);
        ContentSpot near = spot(2L, 37.01, 127.00, 2, false);    // 약 1.1km
        ContentSpot far = spot(3L, 37.20, 127.00, 3, false);     // 약 22km

        List<ScoredSpot> ranked = scorer.rank(List.of(anchor), List.of(far, near), context(3));

        assertThat(ranked).extracting(scored -> scored.spot().placeId()).containsExactly(2L, 3L);
        assertThat(ranked.get(0).addedDetourMeters()).isLessThan(ranked.get(1).addedDetourMeters());
    }

    @Test
    @DisplayName("동선이 비슷하면 recommend_order 가 앞선 쪽이 이긴다")
    void tieBrokenByRecommendOrder() {
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 120, 90, 60);

        ContentSpot anchor = spot(1L, 37.00, 127.00, 1, false);
        ContentSpot early = spot(2L, 37.01, 127.00, 2, false);
        ContentSpot late = spot(3L, 37.01, 127.00, 9, false);    // 같은 좌표, 낮은 순위

        List<ScoredSpot> ranked = scorer.rank(List.of(anchor), List.of(late, early), context(10));

        assertThat(ranked.get(0).spot().placeId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("마지막 자리에 놓이는 후보만 야간 가산점 대상이 된다")
    void onlyTheLastSlotIsEligibleForNightBonus() {
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 120, 90, 60);

        ContentSpot anchor = spot(1L, 37.00, 127.00, 5, false);
        ContentSpot beforeAnchor = spot(2L, 37.01, 127.00, 1, true);   // 앵커보다 앞에 배치
        ContentSpot afterAnchor = spot(3L, 37.01, 127.00, 9, true);    // 마지막에 배치

        List<ScoredSpot> ranked = scorer.rank(List.of(anchor), List.of(beforeAnchor, afterAnchor), context(10));

        assertThat(pick(ranked, 2L).wouldBeLast()).isFalse();
        assertThat(pick(ranked, 2L).nightBonusApplied()).isFalse();
        assertThat(pick(ranked, 3L).wouldBeLast()).isTrue();
    }

    @Test
    @DisplayName("마지막 자리라도 도착 예상 시각이 낮이면 야간 가산점이 붙지 않는다")
    void noNightBonusWhenTheLastSlotLandsInDaytime() {
        // 체류시간 60/60/40 — 도심 코스에서 실제로 마지막 촬영지가 오후 3시경에 끝나던 설정.
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 60, 60, 40);

        ContentSpot anchor = spot(1L, 37.5796, 126.9770, 1, false);       // 경복궁
        ContentSpot nightOpen = spot(2L, 37.5826, 126.9830, 2, true);     // 북촌 (야간 가능)

        List<ScoredSpot> ranked = scorer.rank(List.of(anchor), List.of(nightOpen), context(3));

        ScoredSpot scored = ranked.get(0);
        assertThat(scored.wouldBeLast()).isTrue();
        assertThat(scored.spot().place().isNightOpen()).isTrue();
        assertThat(scored.estimatedLastArrival()).isBefore(LocalTime.of(18, 0));
        assertThat(scored.nightBonusApplied())
                .as("도착이 낮 시간이면 야간 운영 가능 여부는 무의미하다")
                .isFalse();
    }

    @Test
    @DisplayName("체류시간을 늘려 문 닫을 시간에 닿으면 야간 가산점이 붙는다")
    void nightBonusAppliesOnceTheDayRunsPastClosingTime() {
        // 체류시간 120/90/60 — 상향 조정된 현재 기본값. 도심 코스가 18시경에 끝난다.
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 120, 90, 60);

        ContentSpot anchor = spot(1L, 37.5796, 126.9770, 1, false);
        ContentSpot nightOpen = spot(2L, 37.5826, 126.9830, 2, true);

        List<ScoredSpot> ranked = scorer.rank(List.of(anchor), List.of(nightOpen), context(3));

        ScoredSpot scored = ranked.get(0);
        assertThat(scored.estimatedLastArrival()).isAfterOrEqualTo(LocalTime.of(18, 0));
        assertThat(scored.nightBonusApplied()).isTrue();
    }

    @Test
    @DisplayName("문 닫은 뒤 도착하게 되는 후보는 동선이 더 좋아도 뒤로 밀린다")
    void aPlaceThatWillBeClosedLosesEvenWithABetterRoute() {
        // 야간 가중치는 0.1 로 낮게 둔다 — 가중치가 아니라 제약으로 처리되는지 보는 테스트다.
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 120, 90, 60);

        ContentSpot anchor = spot(1L, 37.5796, 126.9770, 1, false);
        // 더 가깝고 recommend_order 도 앞서지만 문을 닫는 곳 (실제 창덕궁 상황)
        ContentSpot closerButClosed = spot(2L, 37.5794, 126.9910, 2, false);
        // 더 멀고 순위도 뒤지만 늦게까지 여는 곳 (실제 인사동 상황)
        ContentSpot fartherButOpen = spot(3L, 37.5740, 126.9856, 3, true);

        List<ScoredSpot> ranked = scorer.rank(
                List.of(anchor), List.of(closerButClosed, fartherButOpen), context(3));

        assertThat(ranked.get(0).spot().placeId())
                .as("동선 몇백 m 차이가 '문이 닫혀 있다'를 이겨서는 안 된다")
                .isEqualTo(3L);
        assertThat(pick(ranked, 2L).closedOnArrival()).isTrue();
        assertThat(pick(ranked, 3L).nightBonusApplied()).isTrue();
    }

    @Test
    @DisplayName("후보가 전부 문을 닫는 곳이면 그중 점수 1등이 그대로 선택된다")
    void whenEveryCandidateClosesTheBestScoredStillWins() {
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 120, 90, 60);

        ContentSpot anchor = spot(1L, 37.5796, 126.9770, 1, false);
        ContentSpot near = spot(2L, 37.5794, 126.9910, 2, false);
        ContentSpot far = spot(3L, 37.7000, 127.2000, 3, false);

        List<ScoredSpot> ranked = scorer.rank(List.of(anchor), List.of(far, near), context(3));

        assertThat(ranked).allSatisfy(scored -> assertThat(scored.closedOnArrival()).isTrue());
        assertThat(ranked.get(0).spot().placeId())
                .as("전부 부적합해도 일정 생성은 되어야 한다")
                .isEqualTo(2L);
    }

    @Test
    @DisplayName("도착이 이른 시각이면 문 닫는 곳이어도 페널티가 없다")
    void noPenaltyWhenArrivingWhileEverythingIsStillOpen() {
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 60, 60, 40);

        ContentSpot anchor = spot(1L, 37.5796, 126.9770, 1, false);
        ContentSpot daytimeOnly = spot(2L, 37.5826, 126.9830, 2, false);

        List<ScoredSpot> ranked = scorer.rank(List.of(anchor), List.of(daytimeOnly), context(3));

        assertThat(ranked.get(0).estimatedLastArrival()).isBefore(LocalTime.of(18, 0));
        assertThat(ranked.get(0).closedOnArrival()).isFalse();
    }

    @Test
    @DisplayName("아직 아무것도 안 골랐으면 동선 페널티가 없고 순위만으로 정해진다")
    void withNothingSelectedOrderDecides() {
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 120, 90, 60);

        ContentSpot first = spot(1L, 37.00, 127.00, 1, false);
        ContentSpot second = spot(2L, 38.00, 128.00, 2, false);

        List<ScoredSpot> ranked = scorer.rank(List.of(), List.of(second, first), context(2));

        assertThat(ranked).allSatisfy(scored -> assertThat(scored.addedDetourMeters()).isZero());
        assertThat(ranked.get(0).spot().placeId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("후보가 없으면 빈 결과")
    void emptyRemainingGivesEmptyResult() {
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 120, 90, 60);

        assertThat(scorer.rank(List.of(spot(1L, 37.0, 127.0, 1, false)), List.of(), context(1))).isEmpty();
    }

    @Test
    @DisplayName("점수는 0~1 범위 안에 있다")
    void scoresAreNormalised() {
        SpotScorer scorer = scorer(0.6, 0.3, 0.1, 120, 90, 60);

        ContentSpot anchor = spot(1L, 37.00, 127.00, 1, false);
        List<ContentSpot> remaining = List.of(
                spot(2L, 37.01, 127.00, 2, true),
                spot(3L, 37.50, 127.00, 3, false),
                spot(4L, 37.05, 127.10, 4, true));

        List<ScoredSpot> ranked = scorer.rank(List.of(anchor), remaining, context(4));

        assertThat(ranked).allSatisfy(scored -> assertThat(scored.score()).isBetween(0.0, 1.0));
    }

    // ── 헬퍼 ────────────────────────────────────────────────────────────────

    private static ScoredSpot pick(List<ScoredSpot> ranked, long placeId) {
        return ranked.stream()
                .filter(scored -> scored.spot().placeId() == placeId)
                .findFirst()
                .orElseThrow();
    }

    private static ContentSpot spot(Long id, double lat, double lng, int recommendOrder, boolean nightOpen) {
        Place place = Place.ofTourApi(
                "ext-" + id, PlaceType.SPOT, "장소" + id, "카테고리", lat, lng, "주소", "지역");
        ReflectionTestUtils.setField(place, "id", id);
        ReflectionTestUtils.setField(place, "nightOpen", nightOpen);
        return new ContentSpot(place, recommendOrder);
    }

    private static ScoringContext context(int totalSpots) {
        return new ScoringContext(totalSpots, Math.min(DayTemplate.MAX_SPOTS, totalSpots), ALLOWANCE);
    }

    private static SpotScorer scorer(double detourWeight, double orderWeight, double nightWeight,
                                     int spotDwell, int restaurantDwell, int cafeDwell) {
        RouteProperties properties = new RouteProperties(
                LocalTime.of(10, 0), spotDwell, restaurantDwell, cafeDwell,
                LocalTime.of(20, 0), LocalTime.of(18, 0),
                ALLOWANCE, 20_000L, 8, 5, detourWeight, orderWeight, nightWeight);
        DetourFilter detourFilter = new DetourFilter(new HaversineDistanceCalculator(25));
        return new SpotScorer(detourFilter, new TimelineEstimator(detourFilter, properties), properties);
    }
}
