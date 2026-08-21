package com.itda.itinerary.route;

import com.itda.common.distance.Coord;
import com.itda.common.distance.DistanceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 구간 A→B 사이에 끼워넣을 후보를 고르는 기하 로직.
 *
 * <p>핵심은 <b>우회거리</b>다:
 * <pre>
 *   우회거리(C) = d(A→C) + d(C→B) − d(A→B)
 * </pre>
 * 이 값이 0에 가까울수록 C 는 A→B 직선 위에 있다. 사용자가 설정하는 '허용거리'는
 * "동선이 몇 m 늘어나도 괜찮은가"이고, 우회거리 ≤ 허용거리 인 점들의 집합이 곧
 * A·B 를 초점으로 하는 <b>타원</b>이다.
 *
 * <p>중간점 반경 원을 쓰지 않는 이유: A·B 가 멀리 떨어져 있으면 중간점 기준 원은
 * 정작 촬영지 바로 옆 맛집을 전부 제외해버린다. 타원은 그런 곳을 자연스럽게 포함한다.
 */
@Component
@RequiredArgsConstructor
public class DetourFilter {

    /** 관광API locationBasedList2 의 radius 상한. */
    public static final long MAX_SEARCH_RADIUS_M = 20_000L;

    /** A·B 가 붙어 있을 때 반경이 0에 수렴하는 것을 막는 하한. */
    public static final long MIN_SEARCH_RADIUS_M = 500L;

    private final DistanceCalculator distanceCalculator;

    /** 우회거리(m). A→B 직선 위면 0, 옆으로 샐수록 커진다. 음수는 0으로 자른다. */
    public long detourMeters(Coord a, Coord candidate, Coord b) {
        long direct = distance(a, b);
        long viaCandidate = distance(a, candidate) + distance(candidate, b);
        return Math.max(0, viaCandidate - direct);
    }

    /** 후보가 허용거리 안쪽(= 타원 내부)인가. */
    public boolean withinEllipse(Coord a, Coord candidate, Coord b, long allowanceMeters) {
        return detourMeters(a, candidate, b) <= allowanceMeters;
    }

    /**
     * 타원을 덮는 검색 원을 만든다.
     *
     * <p>타원의 장반경은 (d(A,B) + 허용거리) / 2 이고 중심은 A·B 의 중간점이므로,
     * 그 반경의 원은 타원을 완전히 포함한다.
     *
     * <p>다만 관광API radius 상한이 20km라 d(A,B) + 허용거리 > 40km 이면 한 원으로
     * 덮을 수 없다. 그때는 A 주변·B 주변 두 원으로 나눈다 — 타원의 중앙부는 빠지지만,
     * 그만큼 떨어진 두 촬영지의 정중앙은 애초에 하루 코스로 들를 곳이 아니다.
     * 이 경우 {@link #isPartialCoverage}가 true 를 돌려주므로 호출부에서 사용자에게 알릴 수 있다.
     */
    public List<SearchArea> searchAreas(Coord a, Coord b, long allowanceMeters) {
        long span = distance(a, b) + allowanceMeters;
        long radius = clampRadius(span / 2);

        if (!isPartialCoverage(a, b, allowanceMeters)) {
            return List.of(new SearchArea(Coord.midpoint(a, b), radius));
        }

        long anchorRadius = clampRadius(allowanceMeters);
        return List.of(new SearchArea(a, anchorRadius), new SearchArea(b, anchorRadius));
    }

    /** 한 개의 원으로 타원을 덮지 못해 앵커 주변 두 원으로 쪼갠 경우인가. */
    public boolean isPartialCoverage(Coord a, Coord b, long allowanceMeters) {
        return (distance(a, b) + allowanceMeters) / 2 > MAX_SEARCH_RADIUS_M;
    }

    /**
     * 하루 마지막 슬롯처럼 뒤쪽 앵커가 없는 구간의 검색 원.
     * 우회거리를 계산할 수 없으므로 앵커 주변을 그냥 가까운 순으로 훑는다.
     */
    public SearchArea trailingSearchArea(Coord anchor, long allowanceMeters) {
        return new SearchArea(anchor, clampRadius(allowanceMeters));
    }

    public long distance(Coord from, Coord to) {
        return distanceCalculator.distanceMeters(
                from.latitude(), from.longitude(), to.latitude(), to.longitude());
    }

    public long durationMinutes(Coord from, Coord to) {
        return distanceCalculator.durationMinutes(
                from.latitude(), from.longitude(), to.latitude(), to.longitude());
    }

    private long clampRadius(long radius) {
        return Math.min(MAX_SEARCH_RADIUS_M, Math.max(MIN_SEARCH_RADIUS_M, radius));
    }
}
