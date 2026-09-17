package com.tourism.itda.planner.route;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.global.tourapi.TourApiCategory;
import com.tourism.itda.global.tourapi.TourApiClient;
import com.tourism.itda.global.tourapi.TourApiPlace;
import com.tourism.itda.place.entity.PlaceType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 작품 관련 명소(앵커)가 3곳에 못 미칠 때 채울 일반 관광명소를 찾는다.
 *
 * <p>일반 명소는 한국관광공사 TourAPI 에서만 가져온다. 관광공사 데이터 활용이 이
 * 프로젝트의 전제이므로 카카오로 대체하지 않는다. 카카오는 식당·카페 후보와 지도·경로
 * 표시에만 쓴다({@link CandidateFinder} 참고). 이 순서를 뒤집지 말 것.
 *
 * <p>관광지(contentTypeId=12) 와 문화시설(14) 를 각각 조회해 합치고, 반경을
 * 5km → 10km → 15km 로 넓혀가며 limit 을 채운다. 15km 까지 넓혀도 못 채우면
 * 찾은 만큼만 돌려준다 — 지방·신도시에서는 그 반경 안에 정말 없을 수 있다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GeneralSpotFinder {

    private static final long[] RADII_METERS = {5_000L, 10_000L, 15_000L};

    private final TourApiClient tourApiClient;
    private final DetourFilter detourFilter;
    private final RouteProperties properties;

    /**
     * 앵커 주변의 일반 관광명소를 가까운 순으로 찾는다.
     *
     * @param anchors 이미 확정된 앵커들. 이 중 중심점을 검색 중심으로 쓴다.
     * @param limit   최대 개수
     */
    public List<NearbySpot> find(List<Coord> anchors, int limit) {
        if (anchors == null || anchors.isEmpty() || limit <= 0) {
            return List.of();
        }
        Coord center = centerOf(anchors);

        // 반경을 넓히면 이전 반경 결과를 포함하므로 externalId 로 중복을 제거하며 누적한다.
        Map<String, NearbySpot> picked = new LinkedHashMap<>();
        long lastRadius = RADII_METERS[0];

        for (long radius : RADII_METERS) {
            lastRadius = radius;
            if (picked.size() >= limit) {
                break;
            }
            SearchArea area = new SearchArea(center, radius);

            for (TourApiPlace place : tourApiClient.findNearby(area, PlaceType.SPOT)) {
                tryAdd(picked, NearbySpot.of(place), anchors, limit);
            }
            for (TourApiPlace place : tourApiClient.findNearbyByContentType(area, TourApiCategory.CONTENT_TYPE_CULTURAL)) {
                tryAdd(picked, NearbySpot.of(place), anchors, limit);
            }
        }

        if (picked.size() < limit) {
            log.info("일반 관광명소를 반경 {}m 까지 넓혔으나 {}곳만 확보했습니다(목표 {}곳).",
                    lastRadius, picked.size(), limit);
        }

        return List.copyOf(picked.values());
    }

    private boolean tryAdd(Map<String, NearbySpot> picked, NearbySpot spot, List<Coord> anchors, int limit) {
        if (picked.size() >= limit) {
            return false;
        }
        if (spot.coord() == null || spot.looksLikeSubFacility()) {
            return false;
        }
        if (spot.category() != null && spot.category().contains("여행코스")) {
            return false;
        }
        // 앵커와 너무 가까우면 같은 관광지의 다른 입구이거나 사실상 같은 자리다.
        // 기준은 앵커끼리의 최소 간격과 같은 값을 쓴다(RoutePlanner 참고).
        long minimum = properties.minSpotSeparationMeters();
        for (Coord anchor : anchors) {
            if (detourFilter.distance(anchor, spot.coord()) < minimum) {
                return false;
            }
        }
        for (NearbySpot existing : picked.values()) {
            if (detourFilter.distance(existing.coord(), spot.coord()) < minimum) {
                return false;
            }
        }

        String key = spot.source() + "|" + spot.externalId();
        if (picked.containsKey(key)) {
            return false;
        }
        picked.put(key, spot);
        return true;
    }

    private static Coord centerOf(List<Coord> anchors) {
        if (anchors.size() == 1) {
            return anchors.get(0);
        }
        double lat = 0;
        double lng = 0;
        for (Coord anchor : anchors) {
            lat += anchor.latitude();
            lng += anchor.longitude();
        }
        return new Coord(lat / anchors.size(), lng / anchors.size());
    }
}
