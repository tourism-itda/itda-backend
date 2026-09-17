package com.tourism.itda.planner.route;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.global.kakao.KakaoLocalClient;
import com.tourism.itda.global.kakao.KakaoPlace;
import com.tourism.itda.global.tourapi.TourApiClient;
import com.tourism.itda.global.tourapi.TourApiPlace;
import com.tourism.itda.place.entity.PlaceType;
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
 * <p>식당·카페도 관광공사 TourAPI 가 1순위다. 카카오는 TourAPI 가 후보 수를 못 채울
 * 때만 보완한다. 카카오 로컬 API 는 앱 설정(카카오맵/로컬 서비스 활성화 여부)에 따라
 * 403 이 날 수 있으므로 카카오 없이도 동작해야 한다 — {@link KakaoLocalClient} 는 실패해도
 * 예외를 던지지 않고 빈 리스트를 돌려주니 이 클래스는 그 위에서 자연스럽게 TourAPI 단독
 * 동작으로 떨어진다.
 *
 * <p>절차:
 * <ol>
 *   <li>타원을 덮는 원으로 TourAPI 를 호출해 넓게 긁어온다</li>
 *   <li>그래도 limit 에 못 미치면 같은 원으로 카카오 카테고리 검색(FD6/CE7)을 보충한다</li>
 *   <li>TourAPI·카카오가 같은 가게를 다른 id 로 줄 수 있어 좌표 50m 이내면 같은 곳으로 보고
 *       먼저 들어간 TourAPI 쪽을 남긴다</li>
 *   <li>우회거리 ≤ 허용거리 인 것만 남긴다 (= 타원 내부)</li>
 *   <li>우회거리 오름차순으로 정렬한다</li>
 * </ol>
 *
 * <p>거리순 정렬만으로는 부족하다. 그건 검색 원의 중심 기준이라 A→B 동선에서 옆으로
 * 새는 가게가 위로 올라올 수 있기 때문이다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateFinder {

    private static final String CATEGORY_RESTAURANT = "FD6";
    private static final String CATEGORY_CAFE = "CE7";

    /** 이 거리 안이면 TourAPI·카카오가 서로 다른 id 로 준 같은 가게로 본다. */
    private static final long SAME_PLACE_RADIUS_M = 50L;

    private final TourApiClient tourApiClient;
    private final KakaoLocalClient kakaoLocalClient;
    private final DetourFilter detourFilter;

    /**
     * 앞뒤 앵커가 모두 있는 구간의 후보.
     *
     * @param start            앞쪽 앵커 (보통 촬영지, 연달아 채울 때는 직전에 고른 장소)
     * @param end              뒤쪽 앵커
     * @param type             찾을 장소 성격
     * @param allowanceMeters  동선이 늘어나도 되는 최대 거리
     * @param excludeExternalIds 이미 쓴/사용자가 거부한 외부 id (TourAPI contentId 또는 카카오 place id)
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

        Collection<NearbySpot> fetched = fetchDistinct(areas, type, excludeExternalIds, limit);

        List<RouteCandidate> candidates = new ArrayList<>();
        for (NearbySpot spot : fetched) {
            long detour = detourFilter.detourMeters(start, spot.coord(), end);
            if (detour <= allowanceMeters) {
                candidates.add(new RouteCandidate(spot, type, detour, true));
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
        Collection<NearbySpot> fetched = fetchDistinct(List.of(area), type, excludeExternalIds, limit);

        List<RouteCandidate> candidates = new ArrayList<>();
        for (NearbySpot spot : fetched) {
            candidates.add(new RouteCandidate(
                    spot, type, detourFilter.distance(anchor, spot.coord()), false));
        }

        candidates.sort(Comparator.comparingLong(RouteCandidate::detourMeters));
        return trim(candidates, limit);
    }

    /**
     * 검색 원이 여러 개일 수 있으므로 중복을 제거하며 모은다.
     *
     * <p>TourAPI 를 먼저 채우고, 그래도 limit 에 못 미칠 때만 카카오로 보충한다.
     */
    private Collection<NearbySpot> fetchDistinct(List<SearchArea> areas,
                                                 PlaceType type,
                                                 Set<String> excludeExternalIds,
                                                 int limit) {
        Map<String, NearbySpot> byKey = new LinkedHashMap<>();

        for (SearchArea area : areas) {
            for (TourApiPlace place : tourApiClient.findNearby(area, type)) {
                addIfNew(byKey, NearbySpot.of(place), excludeExternalIds);
            }
        }

        if (byKey.size() < limit) {
            String categoryGroupCode = categoryGroupCodeFor(type);
            for (SearchArea area : areas) {
                if (byKey.size() >= limit) {
                    break;
                }
                List<KakaoPlace> places = kakaoLocalClient.searchCategory(
                        categoryGroupCode, area.center(), (int) area.radiusMeters(), true);
                for (KakaoPlace place : places) {
                    if (place.looksLikeSubFacility()) {
                        continue;
                    }
                    addIfNew(byKey, NearbySpot.of(place), excludeExternalIds);
                }
            }
        }

        return byKey.values();
    }

    /**
     * 새 장소를 추가한다. 제외 대상이거나, 이미 같은 id 로 들어와 있거나, 이미 담긴
     * 장소와 50m 이내(= 다른 소스가 같은 가게를 준 경우)면 건너뛴다. TourAPI 를 먼저 채운
     * 뒤 카카오로 보충하는 순서라, 좌표가 겹치면 자연히 TourAPI 쪽이 먼저 들어가 남는다.
     */
    private void addIfNew(Map<String, NearbySpot> byKey, NearbySpot spot, Set<String> excludeExternalIds) {
        if (excludeExternalIds.contains(spot.externalId())) {
            return;
        }
        String key = spot.source() + "|" + spot.externalId();
        if (byKey.containsKey(key)) {
            return;
        }
        for (NearbySpot existing : byKey.values()) {
            if (detourFilter.distance(existing.coord(), spot.coord()) < SAME_PLACE_RADIUS_M) {
                return;
            }
        }
        byKey.put(key, spot);
    }

    private static String categoryGroupCodeFor(PlaceType type) {
        return switch (type) {
            case RESTAURANT -> CATEGORY_RESTAURANT;
            case CAFE -> CATEGORY_CAFE;
            case SPOT -> throw new IllegalArgumentException("SPOT 은 식당/카페 후보 조회 대상이 아닙니다.");
        };
    }

    private static List<RouteCandidate> trim(List<RouteCandidate> candidates, int limit) {
        return candidates.size() <= limit ? List.copyOf(candidates) : List.copyOf(candidates.subList(0, limit));
    }
}
