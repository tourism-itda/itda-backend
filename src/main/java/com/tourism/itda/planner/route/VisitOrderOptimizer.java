package com.tourism.itda.planner.route;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.global.kakao.KakaoDirectionsClient;
import com.tourism.itda.global.kakao.KakaoDirectionsClient.RoadLeg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 방문 순서를 정한다. 같은 세 곳이라도 순서에 따라 동선이 크게 달라진다.
 *
 * <p>실제로 겪은 사례: 장영실과학관 → 외암민속마을 → 신정호관광단지 는 직선 13.8km 인데,
 * 신정호를 먼저 들르면 8.4km 다. 신정호가 출발지에서 2.9km 라 갔다가 되돌아오는 모양이었다.
 * 순서를 비교하지 않으면 이런 조합이 그대로 사용자에게 나간다.
 *
 * <p>호출 예산을 지키는 방식:
 * <ol>
 *   <li>모든 순열을 <b>직선거리 합</b>으로 정렬한다 — 여기까지는 API 호출이 0회다</li>
 *   <li>상위 {@code orderVerifyAttempts} 개만 카카오 길찾기로 실측한다</li>
 *   <li>구간·합계 시간 제한을 통과하는 첫 순서를 채택한다</li>
 * </ol>
 * 3곳이면 순열 6개지만 검증은 기본 3개까지, 순서당 2구간이므로 최대 6회 호출이다.
 *
 * <p>자동차는 방향별 경로가 다르므로 역순을 같은 것으로 합치지 않는다.
 * 직선거리 합은 역순이 같지만 실제 도로거리·시간은 다르다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class VisitOrderOptimizer {

    private final DetourFilter detourFilter;
    private final KakaoDirectionsClient directionsClient;
    private final RouteProperties properties;

    /**
     * 확정된 방문 순서.
     *
     * @param roadVerified 카카오 길찾기로 실측했는가. false 면 {@code legs} 가 비어 있고
     *                     거리·시간은 직선 추정이다. 사용자에게 소요시간을 단정하면 안 된다.
     * @param withinLimits 구간 40분·합계 80분 제한을 통과했는가. false 라도 순서 자체는 쓴다 —
     *                     하루 코스로 빡빡하다는 신호이므로 호출부가 안내에 쓸 수 있다.
     */
    public record OrderedRoute(List<ContentSpot> spots,
                               List<RoadLeg> legs,
                               long totalDistanceMeters,
                               long totalDurationSeconds,
                               boolean roadVerified,
                               boolean withinLimits) {
    }

    public OrderedRoute optimize(List<ContentSpot> spots) {
        if (spots.size() < 2) {
            return new OrderedRoute(spots, List.of(), 0L, 0L, false, true);
        }

        List<List<ContentSpot>> candidates = permutations(spots);
        candidates.sort(Comparator.comparingLong(this::straightLineSum));

        int attempts = Math.min(properties.orderVerifyAttempts(), candidates.size());
        OrderedRoute bestVerified = null;

        for (int i = 0; i < attempts; i++) {
            List<ContentSpot> order = candidates.get(i);
            Optional<OrderedRoute> measured = measure(order);
            if (measured.isEmpty()) {
                continue;   // 차로 갈 수 없는 조합 — 다음 순서를 본다
            }

            OrderedRoute route = measured.get();
            if (route.withinLimits()) {
                logChoice(candidates.get(0), route);
                return route;
            }
            if (bestVerified == null || route.totalDurationSeconds() < bestVerified.totalDurationSeconds()) {
                bestVerified = route;
            }
        }

        if (bestVerified != null) {
            log.info("시간 제한을 통과한 순서가 없어 가장 짧은 조합을 씁니다. 합계 {}분",
                    bestVerified.totalDurationSeconds() / 60);
            return bestVerified;
        }

        // 길찾기를 한 번도 못 썼다 (키 없음·전부 실패). 직선 최단 순서로 폴백한다.
        List<ContentSpot> fallback = candidates.get(0);
        long straight = straightLineSum(fallback);
        log.info("길찾기 검증에 실패해 직선 최단 순서로 폴백합니다. 직선 합 {}m", straight);
        return new OrderedRoute(fallback, List.of(), straight, 0L, false, false);
    }

    /** 순서 하나를 실제 도로로 측정한다. 구간이 하나라도 실패하면 이 순서는 버린다. */
    private Optional<OrderedRoute> measure(List<ContentSpot> order) {
        List<RoadLeg> legs = new ArrayList<>(order.size() - 1);
        for (int i = 0; i < order.size() - 1; i++) {
            Optional<RoadLeg> leg = directionsClient.car(order.get(i).coord(), order.get(i + 1).coord());
            if (leg.isEmpty()) {
                return Optional.empty();
            }
            legs.add(leg.get());
        }

        long totalDistance = legs.stream().mapToLong(RoadLeg::distanceMeters).sum();
        long totalDuration = legs.stream().mapToLong(RoadLeg::durationSeconds).sum();
        long longestLeg = legs.stream().mapToLong(RoadLeg::durationSeconds).max().orElse(0L);

        boolean withinLimits = longestLeg <= properties.maxLegDurationSeconds()
                && totalDuration <= properties.maxTotalDurationSeconds();

        return Optional.of(new OrderedRoute(
                order, List.copyOf(legs), totalDistance, totalDuration, true, withinLimits));
    }

    private long straightLineSum(List<ContentSpot> order) {
        long sum = 0;
        for (int i = 0; i < order.size() - 1; i++) {
            sum += detourFilter.distance(order.get(i).coord(), order.get(i + 1).coord());
        }
        return sum;
    }

    /** 직선 최단이 실측 최단과 다를 때만 남긴다. 순서 판정이 의심스러울 때 추적용이다. */
    private void logChoice(List<ContentSpot> straightBest, OrderedRoute chosen) {
        if (!straightBest.equals(chosen.spots())) {
            log.info("직선 최단 순서가 도로 기준으로는 밀렸습니다. 채택: {}",
                    chosen.spots().stream().map(spot -> spot.place().getName()).toList());
        }
    }

    /** 3곳이면 6개. 곳 수가 늘면 폭발하므로 {@link DayTemplate#MAX_SPOTS} 안에서만 쓴다. */
    private static List<List<ContentSpot>> permutations(List<ContentSpot> spots) {
        List<List<ContentSpot>> result = new ArrayList<>();
        permute(new ArrayList<>(spots), new ArrayList<>(), result);
        return result;
    }

    private static void permute(List<ContentSpot> remaining,
                                List<ContentSpot> current,
                                List<List<ContentSpot>> result) {
        if (remaining.isEmpty()) {
            result.add(List.copyOf(current));
            return;
        }
        for (int i = 0; i < remaining.size(); i++) {
            ContentSpot picked = remaining.remove(i);
            current.add(picked);
            permute(remaining, current, result);
            current.remove(current.size() - 1);
            remaining.add(i, picked);
        }
    }

    /** 순서 비교에 쓰는 좌표 추출 — 테스트에서 쓰기 좋게 열어 둔다. */
    static List<Coord> coordsOf(List<ContentSpot> spots) {
        return spots.stream().map(ContentSpot::coord).toList();
    }
}
