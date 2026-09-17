package com.tourism.itda.place.service;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.global.distance.DistanceCalculator;
import com.tourism.itda.global.tourapi.TourApiCategory;
import com.tourism.itda.global.tourapi.TourApiClient;
import com.tourism.itda.global.tourapi.TourApiPlace;
import com.tourism.itda.place.dto.PlaceImageBackfillReport;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceSource;
import com.tourism.itda.place.repository.PlaceRepository;
import com.tourism.itda.planner.route.SearchArea;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 대표 이미지가 없는 장소를 훑어 사진을 채운다.
 *
 * <p>{@code place_image} 에 쓰는 코드가 지금까지 없었던 탓에, 이미 저장된 촬영지·일반명소는
 * 전부 사진이 비어 있다. 임포트 경로를 고쳐도 <b>기존 행은 그대로 비어 있으므로</b>
 * 이 배치를 한 번 돌려야 한다.
 *
 * <p>출처별로 세 단계를 순서대로 시도한다:
 * <ol>
 *   <li><b>contentId</b> — {@code source=TOUR_API} 면 {@code detailCommon2.firstimage}.
 *       실측(시드 앵커 18곳)에서 16곳이 여기서 끝났다.</li>
 *   <li><b>좌표+이름 매칭</b> — 카카오·시드 출처라 contentId 가 없을 때. <b>좌표로 먼저 훑고
 *       이름 유사도로 거른다</b>({@link PlaceNameSimilarity}). 순서가 중요하다:
 *       {@code searchKeyword2} 는 우리 이름이 "거제포로수용소", 관광API 제목이
 *       "거제도 포로수용소" 면 한 글자 차이로 <b>0건</b>을 돌려준다. 반대로 좌표만 믿으면
 *       "학도의용군 전승기념관" 에 근처 죽림사 사진이 붙는다.</li>
 *   <li><b>네이버 이미지 검색</b> — 관광API 가 모르는 곳의 마지막 수단.
 *       {@link NaverPlaceImageFinder} 가 제목 대조로 한 번 더 거른다.</li>
 * </ol>
 *
 * <p>2단계에서 관광API 좌표를 한 번 더 검증하는 이유: 관광API 에도 좌표가 엉뚱한 항목이 있다.
 * "제3땅굴" 은 {@code mapy=19.69, mapx=117.99}(남중국해)로 등록돼 있다.
 *
 * <p>한 장소가 실패해도 배치를 멈추지 않는다. 못 채운 장소는 리포트에 목록으로 남긴다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceImageBackfillBatch {

    /** 우리 좌표에서 이만큼 안에 있는 관광API 항목만 후보로 본다. */
    private static final long MATCH_RADIUS_M = 700L;

    /** 축제·공연·행사. {@link TourApiCategory} 에는 상수가 없어 여기 둔다. */
    private static final String CONTENT_TYPE_FESTIVAL = "15";

    /** 좌표로 훑어볼 관광API 콘텐츠 타입: 관광지 · 문화시설 · 축제/행사. */
    private static final List<String> MATCH_CONTENT_TYPES = List.of(
            TourApiCategory.CONTENT_TYPE_ATTRACTION,
            TourApiCategory.CONTENT_TYPE_CULTURAL,
            CONTENT_TYPE_FESTIVAL);

    /** 이름으로 되짚을 때 훑어볼 후보 수. */
    private static final int NAME_SEARCH_CANDIDATES = 10;

    private final PlaceRepository placeRepository;
    private final PlaceImageService placeImageService;
    private final NaverPlaceImageFinder naverPlaceImageFinder;
    private final TourApiClient tourApiClient;
    private final DistanceCalculator distanceCalculator;

    /**
     * @param maxPlaces     이번 실행에서 시도할 최대 장소 수. 외부 API 호출이 장소당 1~3회라
     *                      한 번에 다 돌리지 말고 나눠 도는 것을 권한다.
     * @param naverFallback 관광API 로 못 찾았을 때 네이버 이미지 검색까지 갈지.
     *                      false 면 관광공사 데이터로 채워지는 비율만 보고 싶을 때 쓴다.
     */
    // 일부러 트랜잭션을 걸지 않는다. 장소당 외부 API 를 1~4회 부르는데 그 전체를 한 트랜잭션으로
    // 묶으면 DB 커넥션을 수 분씩 쥔 채 네트워크를 기다리게 된다. 저장은 장소 한 곳씩
    // PlaceImageService.savePrimaryIfAbsent 가 각자의 트랜잭션으로 처리하므로,
    // 중간에 끊겨도 그때까지 채운 사진은 남는다.
    public PlaceImageBackfillReport run(int maxPlaces, boolean naverFallback) {
        List<Place> targets = placeRepository.findWithoutPrimaryImage(
                PageRequest.of(0, Math.max(1, maxPlaces)));
        log.info("장소 사진 백필 시작 — 대상 {}곳 (네이버 폴백 {})",
                targets.size(), naverFallback ? "사용" : "미사용");

        int byContentId = 0;
        int byNameMatch = 0;
        int byNaver = 0;
        List<PlaceImageBackfillReport.MissingPlace> missing = new ArrayList<>();

        for (Place place : targets) {
            try {
                String url = imageByContentId(place);
                if (url != null) {
                    placeImageService.savePrimaryIfAbsent(place.getId(), url);
                    byContentId++;
                    continue;
                }

                url = imageByNameMatch(place);
                if (url != null) {
                    placeImageService.savePrimaryIfAbsent(place.getId(), url);
                    byNameMatch++;
                    continue;
                }

                url = naverFallback
                        ? naverPlaceImageFinder.find(place.getName(), place.getAddress()).orElse(null)
                        : null;
                if (url != null) {
                    placeImageService.savePrimaryIfAbsent(place.getId(), url);
                    byNaver++;
                    continue;
                }

                missing.add(toMissing(place));

            } catch (Exception e) {
                log.warn("장소 '{}'(id={}) 사진 백필 실패 — 건너뜁니다: {}",
                        place.getName(), place.getId(), e.toString());
                missing.add(toMissing(place));
            }
        }

        log.info("장소 사진 백필 종료 — 시도 {}곳, contentId {}건 / 이름매칭 {}건 / 네이버 {}건, 미해결 {}곳",
                targets.size(), byContentId, byNameMatch, byNaver, missing.size());

        return new PlaceImageBackfillReport(
                targets.size(), byContentId, byNameMatch, byNaver, missing.size(), List.copyOf(missing));
    }

    // ── 단계별 조회 ──────────────────────────────────────────────────────────

    /** 1단계: 관광API contentId 를 이미 들고 있는 장소. */
    private String imageByContentId(Place place) {
        if (place.getSource() != PlaceSource.TOUR_API
                || place.getExternalId() == null || place.getExternalId().isBlank()) {
            return null;
        }
        return tourApiClient.findDetail(place.getExternalId(), place.getPlaceType())
                .map(TourApiPlace::imageUrl)
                .filter(url -> !url.isBlank())
                .orElse(null);
    }

    /**
     * 2단계: 관광API 를 되짚어 같은 곳을 찾는다.
     *
     * <p>좌표 주변을 훑는 쪽이 먼저다. 이름 검색은 한 글자만 달라도 0건이 나오지만
     * 좌표 검색은 이름 표기와 무관하게 후보를 준다. 거기서 이름 유사도로 고른다.
     * 좌표 검색이 빈손이면 이름 검색으로 한 번 더 시도하되, 그때는 관광API 가 준 좌표가
     * 우리 좌표와 맞는지 확인한다 (관광API 에도 좌표가 엉뚱한 항목이 있다).
     */
    private String imageByNameMatch(Place place) {
        String name = place.getName();
        if (name == null || name.isBlank()) {
            return null;
        }

        String byArea = imageFromNearbyArea(place, name);
        return (byArea != null) ? byArea : imageFromTitleSearch(place, name);
    }

    /** 좌표 주변을 콘텐츠 타입별로 훑어 이름이 맞는 것을 고른다. */
    private String imageFromNearbyArea(Place place, String name) {
        SearchArea area = new SearchArea(
                new Coord(place.getLatitude(), place.getLongitude()), MATCH_RADIUS_M);

        for (String contentTypeId : MATCH_CONTENT_TYPES) {
            for (TourApiPlace found : tourApiClient.findNearbyByContentType(area, contentTypeId)) {
                if (found.imageUrl() == null || found.imageUrl().isBlank()) {
                    continue;
                }
                if (PlaceNameSimilarity.matches(name, found.title())) {
                    log.debug("'{}' → 관광API '{}' (좌표 주변, 유사도 {})",
                            name, found.title(), PlaceNameSimilarity.score(name, found.title()));
                    return found.imageUrl();
                }
            }
        }
        return null;
    }

    /** 이름으로 검색하고 좌표로 검증한다. 좌표 주변 검색이 못 찾았을 때의 보조 경로다. */
    private String imageFromTitleSearch(Place place, String name) {
        for (TourApiPlace found : tourApiClient.searchByTitle(name, null, NAME_SEARCH_CANDIDATES)) {
            if (found.imageUrl() == null || found.imageUrl().isBlank() || found.coord() == null) {
                continue;
            }
            if (!PlaceNameSimilarity.matches(name, found.title())) {
                continue;
            }
            long meters = distanceCalculator.distanceMeters(
                    place.getLatitude(), place.getLongitude(),
                    found.coord().latitude(), found.coord().longitude());
            if (meters <= MATCH_RADIUS_M) {
                return found.imageUrl();
            }
            log.debug("'{}' 이름은 맞지만 관광API 좌표가 {}m 떨어져 있어 버립니다 (제목='{}').",
                    name, meters, found.title());
        }
        return null;
    }

    private static PlaceImageBackfillReport.MissingPlace toMissing(Place place) {
        return new PlaceImageBackfillReport.MissingPlace(
                place.getId(), place.getName(), place.getSource().name());
    }
}
