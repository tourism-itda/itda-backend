package com.tourism.itda.place.service;

import com.tourism.itda.global.distance.Coord;
import com.tourism.itda.global.distance.DistanceCalculator;
import com.tourism.itda.global.tourapi.TourApiCategory;
import com.tourism.itda.global.tourapi.TourApiClient;
import com.tourism.itda.global.tourapi.TourApiPlace;
import com.tourism.itda.place.dto.PlaceBackfillReport;
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
import java.util.Optional;

/**
 * 관광API {@code detailCommon2} 로 장소의 <b>대표 사진과 설명</b>을 채운다.
 *
 * <p>둘 다 같은 원인으로 비어 있었다. {@code detailCommon2} 는 {@code firstimage} 와
 * {@code overview} 를 같이 주고 {@link TourApiClient#findDetail} 도 둘 다 읽어 오는데,
 * 저장할 때 둘 다 버리고 있었다. 실제로 "왜고개"(contentId 2758125)는 관광API 에 사진도 있고
 * 239자짜리 설명도 있는데 우리 DB 에는 {@code description = NULL} 이었다.
 *
 * <p>임포트 경로는 고쳤지만 <b>이미 저장된 행은 그대로 비어 있으므로</b> 이 배치를 한 번 돌려야 한다.
 *
 * <p>장소마다 관광API 상세를 어떻게 찾는지가 둘로 갈린다:
 * <ol>
 *   <li><b>contentId 를 이미 들고 있으면</b>({@code source=TOUR_API}) 그대로 단건 조회한다.
 *       실측(시드 앵커 18곳)에서 16곳이 여기서 끝났다.</li>
 *   <li><b>없으면</b>(카카오·시드 출처) 같은 곳을 관광API 에서 찾아 contentId 를 알아낸다.
 *       <b>좌표로 먼저 훑고 이름 유사도로 거른다</b>({@link PlaceNameSimilarity}).
 *       순서가 중요하다: {@code searchKeyword2} 는 우리 이름이 "거제포로수용소", 관광API 제목이
 *       "거제도 포로수용소" 면 한 글자 차이로 <b>0건</b>을 돌려준다. 반대로 좌표만 믿으면
 *       "학도의용군 전승기념관" 에 근처 죽림사가 걸린다.</li>
 * </ol>
 *
 * <p>이름 검색 경로에서 관광API 좌표를 한 번 더 검증하는 이유: 관광API 에도 좌표가 엉뚱한 항목이
 * 있다. "제3땅굴" 은 {@code mapy=19.69, mapx=117.99}(남중국해)로 등록돼 있다.
 *
 * <p>관광API 에서 못 찾은 장소는 <b>그대로 둔다.</b> 카카오로 보충한 식당이 여기 해당하는데,
 * 애초에 관광API 에 없어서 카카오로 넘어온 곳들이라 역매칭이 0/15 였다. 그런 장소의 사진은
 * 응답을 만들 때 {@link PlaceholderImages} 가 분류에 맞는 기본 이미지로 메운다. DB 에는 넣지
 * 않는다 — 넣으면 나중에 관광API 가 그 장소를 갖게 돼도 이 배치가 대상에서 빼 버린다.
 *
 * <p>한 장소가 실패해도 배치를 멈추지 않는다. 못 채운 장소는 리포트에 목록으로 남긴다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceBackfillBatch {

    /** 우리 좌표에서 이만큼 안에 있는 관광API 항목만 같은 곳 후보로 본다. */
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

    /** 장소 한 곳에 저장할 사진 최대 장수. 상세 화면 갤러리용이라 이 정도면 넉넉하다. */
    private static final int MAX_IMAGES_PER_PLACE = 5;

    private final PlaceRepository placeRepository;
    private final PlaceImageService placeImageService;
    private final TourApiClient tourApiClient;
    private final DistanceCalculator distanceCalculator;

    /**
     * @param maxPlaces 이번 실행에서 시도할 최대 장소 수. 외부 API 호출이 장소당 1~5회라
     *                  한 번에 다 돌리지 말고 나눠 도는 것을 권한다.
     */
    // 일부러 트랜잭션을 걸지 않는다. 장소당 외부 API 를 여러 번 부르는데 그 전체를 한 트랜잭션으로
    // 묶으면 DB 커넥션을 수 분씩 쥔 채 네트워크를 기다리게 된다. 저장은 장소 한 곳씩
    // 각자의 트랜잭션으로 처리하므로, 중간에 끊겨도 그때까지 채운 것은 남는다.
    public PlaceBackfillReport run(int maxPlaces) {
        List<Place> targets = placeRepository.findNeedingBackfill(
                PageRequest.of(0, Math.max(1, maxPlaces)));
        log.info("장소 사진·설명 백필 시작 — 대상 {}곳", targets.size());

        int imagesByContentId = 0;
        int imagesByNameMatch = 0;
        int descriptionsFilled = 0;
        List<PlaceBackfillReport.MissingPlace> notFound = new ArrayList<>();

        for (Place place : targets) {
            try {
                boolean hadContentId = hasContentId(place);
                Optional<TourApiPlace> detail = resolveDetail(place);
                if (detail.isEmpty()) {
                    notFound.add(toMissing(place));
                    continue;
                }

                TourApiPlace found = detail.get();

                if (placeImageService.saveImagesIfAbsent(place.getId(), imagesOf(found)) > 0) {
                    if (hadContentId) {
                        imagesByContentId++;
                    } else {
                        imagesByNameMatch++;
                    }
                }

                if (place.fillDescriptionIfBlank(found.overview())) {
                    placeRepository.save(place);
                    descriptionsFilled++;
                }

            } catch (Exception e) {
                log.warn("장소 '{}'(id={}) 백필 실패 — 건너뜁니다: {}",
                        place.getName(), place.getId(), e.toString());
                notFound.add(toMissing(place));
            }
        }

        log.info("장소 사진·설명 백필 종료 — 시도 {}곳, 사진 contentId {}건 / 좌표+이름 {}건, "
                        + "설명 {}건, 관광API 에 없어 기본 이미지로 남는 곳 {}곳",
                targets.size(), imagesByContentId, imagesByNameMatch,
                descriptionsFilled, notFound.size());

        return new PlaceBackfillReport(
                targets.size(), imagesByContentId, imagesByNameMatch,
                descriptionsFilled, notFound.size(), List.copyOf(notFound));
    }

    /**
     * 저장할 사진 목록을 만든다. 대표 사진({@code firstimage})을 맨 앞에 두고
     * {@code detailImage2} 가 준 나머지를 뒤에 붙인다.
     *
     * <p>{@code detailImage2} 를 같이 부르는 이유가 둘이다:
     * <ul>
     *   <li><b>firstimage 가 비어도 사진이 있는 장소가 있다.</b> 전일빌딩245 가 그렇다 —
     *       firstimage 없음, detailImage2 5장.</li>
     *   <li>장소 상세 화면이 {@code place_image} 를 갤러리로 쓰는데 지금은 한 장도 없다.</li>
     * </ul>
     *
     * <p>임포트 경로({@code TourApiPlaceImporter})에서는 부르지 않는다. 루트 생성 중에 장소마다
     * API 호출을 하나 더 붙이면 응답이 느려진다. 갤러리는 이 배치가 채우면 된다.
     */
    private List<String> imagesOf(TourApiPlace found) {
        List<String> urls = new ArrayList<>();
        if (found.imageUrl() != null && !found.imageUrl().isBlank()) {
            urls.add(found.imageUrl());
        }
        if (found.contentId() != null) {
            urls.addAll(tourApiClient.findImages(found.contentId(), MAX_IMAGES_PER_PLACE));
        }
        return urls;
    }

    // ── 관광API 상세 찾기 ─────────────────────────────────────────────────────

    /**
     * 이 장소에 해당하는 관광API 상세를 찾는다. 사진과 설명이 여기서 한꺼번에 나온다
     * ({@code detailCommon2} 가 둘 다 준다).
     */
    private Optional<TourApiPlace> resolveDetail(Place place) {
        if (hasContentId(place)) {
            return tourApiClient.findDetail(place.getExternalId(), place.getPlaceType());
        }
        return matchContentId(place)
                .flatMap(contentId -> tourApiClient.findDetail(contentId, place.getPlaceType()));
    }

    private static boolean hasContentId(Place place) {
        return place.getSource() == PlaceSource.TOUR_API
                && place.getExternalId() != null
                && !place.getExternalId().isBlank();
    }

    /** contentId 가 없는 장소를 관광API 에서 찾아 contentId 를 알아낸다. */
    private Optional<String> matchContentId(Place place) {
        String name = place.getName();
        if (name == null || name.isBlank()) {
            return Optional.empty();
        }
        Optional<String> byArea = contentIdFromNearbyArea(place, name);
        return byArea.isPresent() ? byArea : contentIdFromTitleSearch(place, name);
    }

    /** 좌표 주변을 콘텐츠 타입별로 훑어 이름이 맞는 것을 고른다. */
    private Optional<String> contentIdFromNearbyArea(Place place, String name) {
        SearchArea area = new SearchArea(
                new Coord(place.getLatitude(), place.getLongitude()), MATCH_RADIUS_M);

        for (String contentTypeId : MATCH_CONTENT_TYPES) {
            for (TourApiPlace found : tourApiClient.findNearbyByContentType(area, contentTypeId)) {
                if (PlaceNameSimilarity.matches(name, found.title())) {
                    log.debug("'{}' → 관광API '{}' (좌표 주변, 유사도 {})",
                            name, found.title(), PlaceNameSimilarity.score(name, found.title()));
                    return Optional.ofNullable(found.contentId());
                }
            }
        }
        return Optional.empty();
    }

    /** 이름으로 검색하고 좌표로 검증한다. 좌표 주변 검색이 못 찾았을 때의 보조 경로다. */
    private Optional<String> contentIdFromTitleSearch(Place place, String name) {
        for (TourApiPlace found : tourApiClient.searchByTitle(name, null, NAME_SEARCH_CANDIDATES)) {
            if (found.coord() == null || !PlaceNameSimilarity.matches(name, found.title())) {
                continue;
            }
            long meters = distanceCalculator.distanceMeters(
                    place.getLatitude(), place.getLongitude(),
                    found.coord().latitude(), found.coord().longitude());
            if (meters <= MATCH_RADIUS_M) {
                return Optional.ofNullable(found.contentId());
            }
            log.debug("'{}' 이름은 맞지만 관광API 좌표가 {}m 떨어져 있어 버립니다 (제목='{}').",
                    name, meters, found.title());
        }
        return Optional.empty();
    }

    private static PlaceBackfillReport.MissingPlace toMissing(Place place) {
        return new PlaceBackfillReport.MissingPlace(
                place.getId(), place.getName(), place.getSource().name());
    }
}
