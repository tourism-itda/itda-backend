package com.tourism.itda.planner.discovery;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentPlace;
import com.tourism.itda.content.entity.ContentStatus;
import com.tourism.itda.content.repository.ContentPlaceRepository;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceSource;
import com.tourism.itda.place.entity.PlaceType;
import com.tourism.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link AnchorDiscoveryService} 를 PUBLISHED 작품 전체(또는 미확보 작품)에 돌려 DB 에 적재하는 배치.
 *
 * <p>작품 하나가 실패해도(Claude 오류, API 타임아웃 등) 배치 전체가 멈추면 안 되므로
 * 작품 단위로 try/catch 한다. 호출량 방어를 위해 {@code maxContents} 로 이번 실행 처리 건수를 제한한다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AnchorDiscoveryBatch {

    private final AnchorDiscoveryService anchorDiscoveryService;
    private final DiscoveryContentRepository discoveryContentRepository;
    private final ContentPlaceRepository contentPlaceRepository;
    private final PlaceRepository placeRepository;
    private final DiscoveryPlaceRepository discoveryPlaceRepository;

    /**
     * @param onlyMissing true 면 이미 content_place 가 있는 작품은 건너뛴다
     * @param maxContents 이번 실행에서 처리할 최대 작품 수
     */
    @Transactional
    public DiscoveryReport run(boolean onlyMissing, int maxContents) {
        // PUBLISHED(노출 중) + PENDING(보류 중) 모두 대상. 보류 작품도 장소가 새로 생기면 다시 노출로 승격시킨다.
        List<Content> candidates = discoveryContentRepository.findByStatusInOrderByIdAsc(
                List.of(ContentStatus.PUBLISHED, ContentStatus.PENDING));

        List<Content> targets = new ArrayList<>();
        for (Content content : candidates) {
            if (targets.size() >= maxContents) {
                break;
            }
            if (onlyMissing && !contentPlaceRepository.findByIdContentIdOrderByRecommendOrderAsc(content.getId()).isEmpty()) {
                continue;
            }
            targets.add(content);
        }

        int processed = 0;
        int withAnchors = 0;
        int withZero = 0;
        int placesCreated = 0;
        int mappingsCreated = 0;
        int tourApiVerified = 0;
        int kakaoVerified = 0;
        List<DiscoveryReport.ZeroAnchorContent> zeroAnchorContents = new ArrayList<>();

        log.info("작품 관련 명소 발굴 배치 시작 — 대상 {}편 (onlyMissing={}, maxContents={})",
                targets.size(), onlyMissing, maxContents);

        for (Content content : targets) {
            processed++;
            try {
                List<DiscoveredAnchor> anchors = anchorDiscoveryService.discover(content);

                if (anchors.isEmpty()) {
                    log.info("[{}/{}] '{}' — 관련 명소 0곳", processed, targets.size(), content.getTitle());
                } else {
                    int order = nextRecommendOrder(content);
                    int addedForThisContent = 0;
                    for (DiscoveredAnchor anchor : anchors) {
                        if (anchor.source() == PlaceSource.TOUR_API) {
                            tourApiVerified++;
                        } else {
                            kakaoVerified++;
                        }

                        PlaceUpsertResult upsert = upsertPlace(anchor);
                        if (upsert.created()) {
                            placesCreated++;
                        }

                        if (contentPlaceExists(content.getId(), upsert.place().getId())) {
                            // 이미 이 작품에 매핑된 장소면 건너뛴다.
                            continue;
                        }

                        ContentPlace mapping = new ContentPlace(content, upsert.place(), order++);
                        contentPlaceRepository.save(mapping);
                        mappingsCreated++;
                        addedForThisContent++;
                    }

                    log.info("[{}/{}] '{}' — 관련 명소 {}곳 신규 추가", processed, targets.size(), content.getTitle(), addedForThisContent);
                }

            } catch (Exception e) {
                log.warn("작품 '{}'(id={}) 명소 발굴 처리 실패 — 건너뜁니다: {}",
                        content.getTitle(), content.getId(), e.toString());
            }

            // 관련 장소(content_place)가 하나라도 있으면 노출(PUBLISHED), 하나도 없으면 비노출(PENDING).
            // 관광앱 특성상 "가볼 장소가 없는 작품"은 사용자에게 노출하지 않는다.
            boolean hasPlaces = !contentPlaceRepository
                    .findByIdContentIdOrderByRecommendOrderAsc(content.getId()).isEmpty();
            if (hasPlaces) {
                content.publish();
                withAnchors++;
            } else {
                content.markPending();
                withZero++;
                zeroAnchorContents.add(new DiscoveryReport.ZeroAnchorContent(content.getId(), content.getTitle()));
            }
        }

        log.info("작품 관련 명소 발굴 배치 종료 — 처리 {}편, 명소 확보 {}편, 0곳 {}편, place 신규 {}건, 매핑 신규 {}건 "
                        + "(TourAPI 검증 {}건 / 카카오 검증 {}건)",
                processed, withAnchors, withZero, placesCreated, mappingsCreated, tourApiVerified, kakaoVerified);

        return new DiscoveryReport(
                processed, withAnchors, withZero, placesCreated, mappingsCreated,
                tourApiVerified, kakaoVerified, zeroAnchorContents);
    }

    private int nextRecommendOrder(Content content) {
        List<ContentPlace> existing = contentPlaceRepository.findByIdContentIdOrderByRecommendOrderAsc(content.getId());
        int max = 0;
        for (ContentPlace cp : existing) {
            if (cp.getRecommendOrder() != null && cp.getRecommendOrder() > max) {
                max = cp.getRecommendOrder();
            }
        }
        return max + 1;
    }

    private boolean contentPlaceExists(Long contentId, Long placeId) {
        return contentPlaceRepository.findByIdContentIdOrderByRecommendOrderAsc(contentId).stream()
                .anyMatch(cp -> cp.getId().getPlaceId().equals(placeId));
    }

    /** 이미 같은 출처(kakaoPlaceId / external_id)의 place 가 있으면 재사용하고, 없으면 새로 만든다. */
    private PlaceUpsertResult upsertPlace(DiscoveredAnchor anchor) {
        if (anchor.source() == PlaceSource.TOUR_API) {
            return placeRepository.findBySourceAndExternalId(PlaceSource.TOUR_API, anchor.externalId())
                    .map(place -> new PlaceUpsertResult(place, false))
                    .orElseGet(() -> new PlaceUpsertResult(createTourApiPlace(anchor), true));
        }

        return discoveryPlaceRepository.findByKakaoPlaceId(anchor.externalId())
                .map(place -> new PlaceUpsertResult(place, false))
                .orElseGet(() -> new PlaceUpsertResult(createKakaoPlace(anchor), true));
    }

    private Place createTourApiPlace(DiscoveredAnchor anchor) {
        Place place = Place.ofTourApi(
                anchor.externalId(),
                PlaceType.SPOT,
                anchor.name(),
                anchor.category(),
                // 발굴 사유를 설명으로 남긴다. 관광API overview 는 백필 배치가 뒤에 채운다.
                anchor.reason(),
                anchor.coord().latitude(),
                anchor.coord().longitude(),
                anchor.address(),
                null);
        return placeRepository.save(place);
    }

    private Place createKakaoPlace(DiscoveredAnchor anchor) {
        Place place = Place.ofKakao(
                anchor.externalId(),
                PlaceType.SPOT,
                anchor.name(),
                anchor.category(),
                anchor.reason(),
                anchor.coord().latitude(),
                anchor.coord().longitude(),
                anchor.address(),
                null);
        return placeRepository.save(place);
    }

    private record PlaceUpsertResult(Place place, boolean created) {
    }
}
