package com.itda.itinerary.route;

import com.itda.common.distance.Coord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 사용자가 3곳을 다 고르지 않았을 때 어떤 촬영지를 채워 넣을지 점수를 매긴다.
 *
 * <p>이 단계가 LLM 보다 먼저 오는 것이 중요하다. LLM 은 좌표 계산을 못 하므로
 * "동선상 말이 되는가"는 반드시 코드가 판정해야 한다. LLM 은 그렇게 좁혀진
 * 후보들 중에서 "같이 보면 좋은 조합인가"만 고른다 ({@link SpotCurator}).
 *
 * <p>점수 = 동선 + 콘텐츠 내 중요도 + 야간 운영 가능 여부. 가중치는 설정으로 조정 가능하다.
 *
 * <p><b>야간 가산점은 도착 예상 시각을 확인하고 준다.</b> 템플릿상 마지막이라는 이유만으로
 * 가산점을 주면, 실제로는 오후 3시에 도착하는 자리에 "야간 운영 가능"을 우대하게 된다.
 * 촬영지가 서로 가까운 도심 코스에서 실제로 그런 일이 벌어졌다.
 */
@Component
@RequiredArgsConstructor
public class SpotScorer {

    private final DetourFilter detourFilter;
    private final TimelineEstimator timelineEstimator;
    private final RouteProperties properties;

    /**
     * 남은 촬영지들에 점수를 매겨 높은 순으로 돌려준다.
     *
     * @param selected  이미 확정된 촬영지들 (recommend_order 오름차순이어야 한다)
     * @param remaining 후보 촬영지들
     */
    public List<ScoredSpot> rank(List<ContentSpot> selected, List<ContentSpot> remaining, ScoringContext context) {
        if (remaining.isEmpty()) {
            return List.of();
        }

        List<Draft> drafts = new ArrayList<>(remaining.size());
        long maxDetour = 0;
        for (ContentSpot candidate : remaining) {
            int insertAt = insertIndexOf(selected, candidate.recommendOrder());
            long detour = addedDetour(selected, candidate, insertAt);
            boolean last = (insertAt == selected.size());
            LocalTime lastArrival = estimateLastArrival(selected, candidate, insertAt, context);

            drafts.add(new Draft(candidate, detour, last, lastArrival));
            maxDetour = Math.max(maxDetour, detour);
        }

        double totalWeight = properties.detourWeight() + properties.orderWeight() + properties.nightWeight();
        if (totalWeight <= 0) {
            totalWeight = 1;
        }

        List<ScoredSpot> scored = new ArrayList<>(drafts.size());
        for (Draft draft : drafts) {
            double detourScore = (maxDetour == 0) ? 1.0 : 1.0 - ((double) draft.detour / maxDetour);
            double orderScore = orderScore(draft.spot.recommendOrder(), context.totalSpots());

            // 마지막 자리에 늦게 도착하는 상황인가. 도착이 오후 3시라면 야간 운영
            // 가능 여부는 아무 의미가 없으므로 가산점도 페널티도 없다.
            boolean arrivesLate = draft.last
                    && !draft.lastArrival.isBefore(properties.lateArrivalThreshold());
            boolean nightBonus = arrivesLate && draft.spot.place().isNightOpen();
            boolean closedOnArrival = arrivesLate && !draft.spot.place().isNightOpen();

            double score = (properties.detourWeight() * detourScore
                    + properties.orderWeight() * orderScore
                    + properties.nightWeight() * (nightBonus ? 1.0 : 0.0)) / totalWeight;

            scored.add(new ScoredSpot(draft.spot, draft.detour, draft.last, draft.lastArrival,
                    nightBonus, closedOnArrival, score));
        }

        // 문 닫은 뒤 도착하게 되는 후보는 점수와 무관하게 뒤로 민다.
        // 가중치로 처리하면 동선 몇백 m 차이에 밀려 닫힌 곳이 선택된다 — 실제로 그랬다.
        // 다만 후보가 전부 그런 경우엔 그중 점수 1등이 그대로 선택된다 (하드 필터가 아니다).
        scored.sort(Comparator.comparing(ScoredSpot::closedOnArrival)
                .thenComparing(Comparator.comparingDouble(ScoredSpot::score).reversed())
                .thenComparingLong(ScoredSpot::addedDetourMeters));
        return List.copyOf(scored);
    }

    /**
     * 이 후보를 넣었다고 가정했을 때 하루 마지막 칸의 도착 예상 시각.
     *
     * <p>아직 다 고르지 않은 중간 상태라 이동시간 일부가 빠지고, 그만큼 실제보다 이르게
     * 나온다. 야간 가산점이 과하게 붙는 것보다 덜 붙는 쪽이 안전하므로 그대로 둔다.
     */
    private LocalTime estimateLastArrival(List<ContentSpot> selected,
                                          ContentSpot candidate,
                                          int insertAt,
                                          ScoringContext context) {
        List<Coord> coords = new ArrayList<>(selected.size() + 1);
        selected.forEach(spot -> coords.add(spot.coord()));
        coords.add(Math.min(insertAt, coords.size()), candidate.coord());

        return timelineEstimator.lastSlotArrival(
                context.targetSpotCount(), coords, context.allowanceMeters());
    }

    /**
     * 후보를 recommend_order 순서상 제자리에 끼워 넣었을 때 늘어나는 거리.
     * 앞이나 뒤 끝에 붙는 경우엔 한쪽 이웃까지의 거리만 더해진다.
     */
    private long addedDetour(List<ContentSpot> selected, ContentSpot candidate, int insertAt) {
        if (selected.isEmpty()) {
            return 0L;
        }
        if (insertAt == 0) {
            return detourFilter.distance(candidate.coord(), selected.get(0).coord());
        }
        if (insertAt == selected.size()) {
            return detourFilter.distance(selected.get(insertAt - 1).coord(), candidate.coord());
        }
        return detourFilter.detourMeters(
                selected.get(insertAt - 1).coord(), candidate.coord(), selected.get(insertAt).coord());
    }

    /** recommend_order 오름차순 리스트에서 이 순위가 들어갈 자리. */
    private static int insertIndexOf(List<ContentSpot> selected, int recommendOrder) {
        int index = 0;
        while (index < selected.size() && selected.get(index).recommendOrder() < recommendOrder) {
            index++;
        }
        return index;
    }

    /** recommend_order 가 앞일수록 1에 가깝게. */
    private static double orderScore(int recommendOrder, int totalSpots) {
        if (totalSpots <= 1) {
            return 1.0;
        }
        double raw = 1.0 - ((double) (recommendOrder - 1) / (totalSpots - 1));
        return Math.max(0.0, Math.min(1.0, raw));
    }

    private record Draft(ContentSpot spot, long detour, boolean last, LocalTime lastArrival) {
    }
}
