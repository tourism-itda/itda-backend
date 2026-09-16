package com.tourism.itda.planner.route;

import com.tourism.itda.content.entity.ContentPlace;
import com.tourism.itda.content.repository.ContentPlaceRepository;
import com.tourism.itda.explore.entity.ContentPerson;
import com.tourism.itda.explore.entity.PlacePerson;
import com.tourism.itda.explore.repository.ContentPersonRepository;
import com.tourism.itda.explore.repository.PlacePersonRepository;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceType;
import com.tourism.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 작품 관련 명소(앵커)를 찾는다. 루트의 출발점이라 여기서 0곳이면 루트 자체가 없다.
 *
 * <p>두 경로를 순서대로 시도한다:
 * <ol>
 *   <li>{@code content_place} — 사람이 승인한 매핑. 가장 신뢰도가 높아 있으면 무조건 이걸 쓴다.</li>
 *   <li>{@code content_person → place_person} — 작품에 붙은 실존 인물의 연고지.
 *       수집 때 {@code ContentPerson} 이 자동으로 쌓이므로 <b>새 작품이 들어와도 그냥 돈다</b>.</li>
 * </ol>
 *
 * <p>인물 경로는 "인물이 같다"는 근거뿐이라 촬영지가 아니다. 호출부는 이 차이를
 * {@link AnchorSource} 로 구분해 사용자에게 다르게 설명해야 한다.
 *
 * <p>앵커가 전국에 흩어져 있으면 하루 코스가 될 수 없으므로
 * {@link #densestCluster} 로 한 지역만 남긴다. 멀리 떨어진 앵커를 억지로 잇지 않는다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentAnchorResolver {

    private final ContentPlaceRepository contentPlaceRepository;
    private final ContentPersonRepository contentPersonRepository;
    private final PlacePersonRepository placePersonRepository;
    private final PlaceRepository placeRepository;
    private final DetourFilter detourFilter;
    private final RouteProperties properties;

    /** 앵커를 어디서 얻었는가. 사용자에게 붙일 설명 문구가 달라진다. */
    public enum AnchorSource {
        /** 승인된 작품–장소 매핑. "작품의 장소"라고 말해도 되는 유일한 등급. */
        CONTENT_PLACE,
        /** 작품 인물의 연고지. "작품 속 인물과 연결된 곳"까지만 말할 수 있다. */
        PERSON_CHAIN,
        /** 아무것도 없음 — 루트를 만들지 않고 숨긴다. */
        NONE
    }

    public record Anchors(List<ContentSpot> spots, AnchorSource source) {

        public boolean isEmpty() {
            return spots.isEmpty();
        }

        static Anchors none() {
            return new Anchors(List.of(), AnchorSource.NONE);
        }
    }

    public Anchors resolve(Long contentId) {
        List<ContentSpot> approved = fromContentPlace(contentId);
        if (!approved.isEmpty()) {
            return new Anchors(clusterAndOrder(approved), AnchorSource.CONTENT_PLACE);
        }

        List<ContentSpot> viaPerson = fromPersonChain(contentId);
        if (!viaPerson.isEmpty()) {
            return new Anchors(clusterAndOrder(viaPerson), AnchorSource.PERSON_CHAIN);
        }

        log.info("작품 {} 의 앵커가 0곳입니다. content_place·인물 체인 모두 비었습니다.", contentId);
        return Anchors.none();
    }

    // ── 경로 1: 승인된 매핑 ───────────────────────────────────────────────────

    private List<ContentSpot> fromContentPlace(Long contentId) {
        List<ContentPlace> mappings =
                contentPlaceRepository.findByIdContentIdOrderByRecommendOrderAsc(contentId);
        if (mappings.isEmpty()) {
            return List.of();
        }

        Map<Long, Place> places = loadPlaces(
                mappings.stream().map(m -> m.getId().getPlaceId()).toList());

        List<ContentSpot> spots = new ArrayList<>(mappings.size());
        for (ContentPlace mapping : mappings) {
            Place place = places.get(mapping.getId().getPlaceId());
            if (place != null) {
                spots.add(new ContentSpot(place, mapping.getRecommendOrder()));
            }
        }
        return spots;
    }

    // ── 경로 2: 인물 체인 ────────────────────────────────────────────────────

    /**
     * 작품 → 인물 → 장소. 한 인물이 여러 장소를, 한 장소가 여러 인물을 가질 수 있어
     * place_id 로 중복을 제거한다.
     *
     * <p>{@code recommend_order} 가 없으므로 인물 등장 순서를 그대로 순번으로 쓴다.
     * 실제 방문 순서는 뒤에서 동선 기준으로 다시 정렬되므로 여기서는 안정적인 값이면 된다.
     */
    private List<ContentSpot> fromPersonChain(Long contentId) {
        List<ContentPerson> links = contentPersonRepository.findByContentId(contentId);
        if (links.isEmpty()) {
            return List.of();
        }

        List<Long> personIds = links.stream()
                .map(link -> link.getPerson().getPersonId())
                .distinct()
                .toList();

        List<PlacePerson> placeLinks = placePersonRepository.findByPersonPersonIdIn(personIds);
        if (placeLinks.isEmpty()) {
            return List.of();
        }

        List<Long> placeIds = placeLinks.stream()
                .map(link -> link.getPlace().getId())
                .distinct()
                .toList();
        Map<Long, Place> places = loadPlaces(placeIds);

        List<ContentSpot> spots = new ArrayList<>();
        int order = 1;
        for (Long placeId : placeIds) {
            Place place = places.get(placeId);
            // 식당·카페는 앵커가 아니다. 좌표 없는 행은 거리 계산을 오염시키므로 버린다.
            if (place == null || place.getPlaceType() != PlaceType.SPOT || !hasUsableCoord(place)) {
                continue;
            }
            spots.add(new ContentSpot(place, order++));
        }
        return spots;
    }

    // ── 지역 묶기 ────────────────────────────────────────────────────────────

    /**
     * 가장 많은 앵커가 모여 있는 지역 하나만 남기고, recommend_order 로 정렬한다.
     *
     * <p>각 앵커를 중심으로 반경 안에 들어오는 이웃 수를 세고 가장 큰 무리를 고른다.
     * 동점이면 서로 더 가까운 쪽이 이긴다. 앵커가 2곳 이하면 그대로 둔다 —
     * 이 경우 거리 판정은 뒤의 동선 검증이 맡는다.
     */
    private List<ContentSpot> clusterAndOrder(List<ContentSpot> spots) {
        List<ContentSpot> clustered = densestCluster(spots);
        return clustered.stream()
                .sorted(Comparator.comparingInt(ContentSpot::recommendOrder))
                .toList();
    }

    private List<ContentSpot> densestCluster(List<ContentSpot> spots) {
        if (spots.size() <= 2) {
            return spots;
        }

        long span = properties.anchorClusterSpanMeters();
        List<ContentSpot> best = null;
        long bestSpread = Long.MAX_VALUE;

        for (ContentSpot seed : spots) {
            List<ContentSpot> group = spots.stream()
                    .filter(other -> detourFilter.distance(seed.coord(), other.coord()) <= span)
                    .toList();
            long spread = group.stream()
                    .mapToLong(other -> detourFilter.distance(seed.coord(), other.coord()))
                    .sum();

            if (best == null || group.size() > best.size()
                    || (group.size() == best.size() && spread < bestSpread)) {
                best = group;
                bestSpread = spread;
            }
        }

        if (best.size() < spots.size()) {
            Set<Long> kept = best.stream().map(ContentSpot::placeId).collect(Collectors.toSet());
            log.info("앵커 {}곳 중 {}곳만 한 지역({}m 이내)에 모여 있어 나머지는 제외합니다. 남긴 place_id={}",
                    spots.size(), best.size(), span, kept);
        }
        return best;
    }

    // ── 공통 ────────────────────────────────────────────────────────────────

    private Map<Long, Place> loadPlaces(List<Long> placeIds) {
        if (placeIds.isEmpty()) {
            return Map.of();
        }
        return placeRepository.findAllById(placeIds).stream()
                .collect(Collectors.toMap(Place::getId, place -> place, (a, b) -> a, LinkedHashMap::new));
    }

    /** 0,0 같은 미입력 좌표를 거른다. 국내 범위를 크게 벗어난 값도 데이터 오류로 본다. */
    private static boolean hasUsableCoord(Place place) {
        double lat = place.getLatitude();
        double lng = place.getLongitude();
        return lat >= 33 && lat <= 39 && lng >= 124 && lng <= 132;
    }
}
