package com.itda.itinerary.route;

import com.itda.common.distance.Coord;
import com.itda.common.tourapi.TourApiClient;
import com.itda.common.tourapi.TourApiPlace;
import com.itda.place.domain.PlaceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 구간 하나에 들어갈 식당/카페 후보를 찾아 동선 좋은 순으로 정렬한다.
 *
 * <p>절차:
 * <ol>
 *   <li>타원을 덮는 원으로 관광API 를 호출해 넓게 긁어온다</li>
 *   <li>우회거리 ≤ 허용거리 인 것만 남긴다 (= 타원 내부)</li>
 *   <li>우회거리 오름차순으로 정렬한다</li>
 * </ol>
 *
 * <p>관광API 의 {@code arrange=E}(거리순)만으로는 부족하다. 그건 검색 원의 중심 기준이라
 * A→B 동선에서 옆으로 새는 가게가 위로 올라올 수 있기 때문이다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateFinder {

    private final TourApiClient tourApiClient;
    private final DetourFilter detourFilter;

    /**
     * 앞뒤 앵커가 모두 있는 구간의 후보.
     *
     * @param start            앞쪽 앵커 (보통 촬영지, 연달아 채울 때는 직전에 고른 장소)
     * @param end              뒤쪽 앵커
     * @param type             찾을 장소 성격
     * @param allowanceMeters  동선이 늘어나도 되는 최대 거리
     * @param excludeExternalIds 이미 쓴/사용자가 거부한 관광API contentId
     * @param limit            최대 개수
     */
    public List<RouteCandidate> findBetween(Coord start,
                                            Coord end,
                                            PlaceType type,
                                            long allowanceMeters,
                                            Set<String> excludeExternalIds,
                                            int limit) {
        List<SearchArea> areas = detourFilter.searchAreas(start, end, allowanceMeters);
        if (detourFilter.isPartialCoverage(start, end, allowanceMeters)) {
            log.info("두 촬영지가 멀어({}m) 타원을 원 하나로 덮지 못합니다. 앵커 주변 두 원으로 나눠 검색합니다.",
                    detourFilter.distance(start, end));
        }

        Collection<TourApiPlace> fetched = fetchDistinct(areas, type, excludeExternalIds);

        List<RouteCandidate> candidates = new ArrayList<>();
        for (TourApiPlace place : fetched) {
            long detour = detourFilter.detourMeters(start, place.coord(), end);
            if (detour <= allowanceMeters) {
                candidates.add(new RouteCandidate(place, detour, true));
            }
        }

        candidates.sort(Comparator.comparingLong(RouteCandidate::detourMeters));
        return trim(candidates, limit);
    }

    /**
     * 뒤쪽 앵커가 없는 꼬리 구간(하루 마지막)의 후보.
     * 우회거리를 정의할 수 없으므로 앵커에서 가까운 순으로 준다.
     */
    public List<RouteCandidate> findAround(Coord anchor,
                                           PlaceType type,
                                           long allowanceMeters,
                                           Set<String> excludeExternalIds,
                                           int limit) {
        SearchArea area = detourFilter.trailingSearchArea(anchor, allowanceMeters);
        Collection<TourApiPlace> fetched = fetchDistinct(List.of(area), type, excludeExternalIds);

        List<RouteCandidate> candidates = new ArrayList<>();
        for (TourApiPlace place : fetched) {
            candidates.add(new RouteCandidate(place, detourFilter.distance(anchor, place.coord()), false));
        }

        candidates.sort(Comparator.comparingLong(RouteCandidate::detourMeters));
        return trim(candidates, limit);
    }

    /** 검색 원이 여러 개일 수 있으므로 contentId 기준으로 중복을 제거하며 모은다. */
    private Collection<TourApiPlace> fetchDistinct(List<SearchArea> areas,
                                                   PlaceType type,
                                                   Set<String> excludeExternalIds) {
        Map<String, TourApiPlace> byContentId = new LinkedHashMap<>();
        for (SearchArea area : areas) {
            for (TourApiPlace place : tourApiClient.findNearby(area, type)) {
                if (excludeExternalIds.contains(place.contentId())) {
                    continue;
                }
                byContentId.putIfAbsent(place.contentId(), place);
            }
        }
        return byContentId.values();
    }

    private static List<RouteCandidate> trim(List<RouteCandidate> candidates, int limit) {
        return candidates.size() <= limit ? List.copyOf(candidates) : List.copyOf(candidates.subList(0, limit));
    }
}
