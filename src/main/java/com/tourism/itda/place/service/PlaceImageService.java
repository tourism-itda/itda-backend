package com.tourism.itda.place.service;

import com.tourism.itda.place.dto.PlaceImageStatusResponse;
import com.tourism.itda.place.entity.PlaceImage;
import com.tourism.itda.place.entity.PlaceSource;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Optional<String> primaryImageUrl(Long placeId) {
        if (placeId == null) {
            return Optional.empty();
        }
        return placeImageRepository.findFirstByPlaceIdAndPrimaryIsTrueOrderBySortOrderAsc(placeId)
                .map(PlaceImage::getImageUrl);
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

        return new PlaceImageStatusResponse(total, withImage, total - withImage, List.copyOf(rows));
    }

    private static Map<PlaceSource, Long> toMap(List<PlaceRepository.SourceCount> counts) {
        Map<PlaceSource, Long> map = new EnumMap<>(PlaceSource.class);
        for (PlaceRepository.SourceCount count : counts) {
            map.put(count.getSource(), count.getTotal());
        }
        return map;
    }
}
