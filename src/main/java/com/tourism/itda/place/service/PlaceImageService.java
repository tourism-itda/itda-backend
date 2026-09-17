package com.tourism.itda.place.service;

import com.tourism.itda.place.dto.PlaceImageStatusResponse;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceImage;
import com.tourism.itda.place.entity.PlaceSource;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 장소 대표 이미지를 저장·조회하는 단일 창구.
 *
 * <p>{@code place_image} 에 쓰는 코드가 지금까지 하나도 없어서 루트 응답의
 * {@code image_url} 이 사실상 항상 null 이었다. 장소를 저장하는 모든 경로
 * (TourAPI 임포트 · 카카오 임포트 · 백필 배치)가 여기를 거치게 해서
 * "저장했는데 사진만 빠지는" 상황이 다시 생기지 않게 한다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceImageService {

    private final PlaceImageRepository placeImageRepository;
    private final PlaceRepository placeRepository;

    /**
     * 대표 이미지가 아직 없을 때만 저장한다.
     *
     * <p>이미 있으면 덮어쓰지 않는다 — 먼저 들어간 쪽이 더 믿을 만한 출처이기 때문이다
     * (TourAPI {@code firstimage} → 네이버 이미지 검색 순으로 시도한다).
     *
     * @return 새로 저장했으면 true
     */
    @Transactional
    public boolean savePrimaryIfAbsent(Long placeId, String imageUrl) {
        if (placeId == null || imageUrl == null || imageUrl.isBlank()) {
            return false;
        }
        if (placeImageRepository.findFirstByPlaceIdAndPrimaryIsTrueOrderBySortOrderAsc(placeId).isPresent()) {
            return false;
        }
        placeImageRepository.save(PlaceImage.primaryOf(placeId, imageUrl));
        log.debug("place {} 대표 이미지 저장: {}", placeId, imageUrl);
        return true;
    }

    /**
     * 사진 여러 장을 한 번에 저장한다. 첫 장이 대표가 된다.
     *
     * <p>이미 대표 이미지가 있으면 아무것도 하지 않는다 — 갤러리를 두 번 쌓지 않기 위해서다.
     * 장소 상세 화면이 {@code sortOrder} 순으로 갤러리를 만들므로 받은 순서를 그대로 쓴다.
     *
     * @return 저장한 장수
     */
    @Transactional
    public int saveImagesIfAbsent(Long placeId, List<String> imageUrls) {
        if (placeId == null || imageUrls == null || imageUrls.isEmpty()) {
            return 0;
        }
        if (placeImageRepository.findFirstByPlaceIdAndPrimaryIsTrueOrderBySortOrderAsc(placeId).isPresent()) {
            return 0;
        }

        List<String> distinct = imageUrls.stream()
                .filter(url -> url != null && !url.isBlank())
                .distinct()
                .toList();
        if (distinct.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < distinct.size(); i++) {
            placeImageRepository.save(PlaceImage.of(placeId, distinct.get(i), i == 0, i));
        }
        log.debug("place {} 사진 {}장 저장", placeId, distinct.size());
        return distinct.size();
    }

    @Transactional(readOnly = true)
    public Optional<String> primaryImageUrl(Long placeId) {
        if (placeId == null) {
            return Optional.empty();
        }
        return placeImageRepository.findFirstByPlaceIdAndPrimaryIsTrueOrderBySortOrderAsc(placeId)
                .map(PlaceImage::getImageUrl);
    }

    /**
     * 저장된 사진이 있으면 그것을, 없으면 분류에 맞는 기본 이미지를 준다.
     *
     * <p>사진 없는 카드가 화면에서 깨져 보이지 않게 하는 것이 목적이다. 기본 이미지는
     * <b>DB 에 저장하지 않고 여기서만 끼워 넣는다</b> — 저장하면 진짜 사진과 구분이 안 되고,
     * 나중에 관광API 가 그 장소 사진을 갖게 돼도 백필이 대상에서 빼 버린다.
     */
    @Transactional(readOnly = true)
    public String imageUrlOrPlaceholder(Place place) {
        return primaryImageUrl(place.getId())
                .orElseGet(() -> PlaceholderImages.forPlace(place.getPlaceType(), place.getCategory()));
    }

    /**
     * 여러 장소의 대표 이미지를 한 번에. 사진이 없는 장소는 기본 이미지로 채워 돌려준다
     * (따라서 <b>모든 place_id 에 값이 있다</b>).
     */
    @Transactional(readOnly = true)
    public Map<Long, String> imageUrlsOrPlaceholders(Collection<Place> places) {
        List<Long> ids = places.stream().map(Place::getId).filter(Objects::nonNull).toList();
        Map<Long, String> stored = ids.isEmpty()
                ? Map.of()
                : placeImageRepository.findByPlaceIdInAndPrimaryIsTrue(ids).stream()
                        .collect(Collectors.toMap(
                                PlaceImage::getPlaceId, PlaceImage::getImageUrl, (a, b) -> a));

        Map<Long, String> result = new LinkedHashMap<>();
        for (Place place : places) {
            if (place.getId() == null) {
                continue;
            }
            String url = stored.get(place.getId());
            result.put(place.getId(), url != null
                    ? url
                    : PlaceholderImages.forPlace(place.getPlaceType(), place.getCategory()));
        }
        return result;
    }

    /**
     * 사진이 채워진 정도를 출처별로 집계한다. 백필을 돌리기 전후로 확인하는 용도다.
     *
     * <p>출처가 하나도 없는 경우(예: KAKAO 장소가 아직 없음)는 줄을 만들지 않는다 —
     * 0 으로 채운 줄을 만들면 실제로 뭘 갖고 있는지가 흐려진다.
     */
    @Transactional(readOnly = true)
    public PlaceImageStatusResponse status() {
        Map<PlaceSource, Long> totals = toMap(placeRepository.countBySource());
        Map<PlaceSource, Long> withImages = toMap(placeRepository.countWithPrimaryImageBySource());

        long total = 0;
        long withImage = 0;
        List<PlaceImageStatusResponse.SourceStatus> rows = new ArrayList<>();

        for (PlaceSource source : PlaceSource.values()) {
            long sourceTotal = totals.getOrDefault(source, 0L);
            if (sourceTotal == 0) {
                continue;
            }
            long sourceWithImage = withImages.getOrDefault(source, 0L);
            total += sourceTotal;
            withImage += sourceWithImage;
            rows.add(new PlaceImageStatusResponse.SourceStatus(
                    source.name(), sourceTotal, sourceWithImage, sourceTotal - sourceWithImage));
        }

        return new PlaceImageStatusResponse(
                total, withImage, total - withImage,
                placeRepository.countWithoutDescription(),
                List.copyOf(rows));
    }

    private static Map<PlaceSource, Long> toMap(List<PlaceRepository.SourceCount> counts) {
        Map<PlaceSource, Long> map = new EnumMap<>(PlaceSource.class);
        for (PlaceRepository.SourceCount count : counts) {
            map.put(count.getSource(), count.getTotal());
        }
        return map;
    }
}
