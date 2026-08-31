package com.tourism.itda.place.service;

import com.tourism.itda.content.entity.ContentPlace;
import com.tourism.itda.content.repository.ContentPlaceRepository;
import com.tourism.itda.content.repository.BookmarkRepository;
import com.tourism.itda.global.exception.NotFoundException;
import com.tourism.itda.place.dto.AlternativePlaceResponse;
import com.tourism.itda.place.dto.PlaceDetailResponse;
import com.tourism.itda.place.dto.PlaceImageDto;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 저장된 place 테이블을 조회하는 서비스 (명세 No.25, No.26).
 *
 * <p>같은 패키지의 {@link PlaceService} 와 역할이 다르다:
 * <ul>
 *   <li>{@code PlaceService} — 한국관광공사 API 패스스루. DB 를 거치지 않는다.</li>
 *   <li>이 클래스 — 우리 DB 의 {@code place} / {@code place_image} 조회.</li>
 * </ul>
 * 한 클래스에 섞으면 관광API 쪽 코드와 충돌이 잦아져 분리해 두었다.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceQueryService {

    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;
    private final ContentPlaceRepository contentPlaceRepository;
    private final BookmarkRepository bookmarkRepository;

    /** No.25 장소 상세 (인증 선택). userId 가 null 이면 비로그인 → is_bookmarked=false. */
    public PlaceDetailResponse getPlaceDetail(Long placeId, Long userId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new NotFoundException("장소를 찾을 수 없습니다."));

        List<PlaceImageDto> images = placeImageRepository
                .findByPlaceIdOrderBySortOrderAsc(placeId).stream()
                .map(PlaceImageDto::from)
                .toList();

        boolean isBookmarked = userId != null
                && bookmarkRepository.existsByUserIdAndPlaceId(userId, placeId);

        return PlaceDetailResponse.of(place, images, isBookmarked);
    }

    /** No.26 일정 슬롯 대안 장소 ('다른 곳 추천'). */
    public AlternativePlaceResponse getAlternative(Long contentId, int visitOrder, Long excludePlaceId) {
        ContentPlace next = (excludePlaceId != null
                ? contentPlaceRepository
                    .findFirstByIdContentIdAndRecommendOrderGreaterThanAndIdPlaceIdNotOrderByRecommendOrderAsc(
                            contentId, visitOrder, excludePlaceId)
                : contentPlaceRepository
                    .findFirstByIdContentIdAndRecommendOrderGreaterThanOrderByRecommendOrderAsc(
                            contentId, visitOrder))
                .orElseThrow(() -> new NotFoundException("더 이상 추천할 대안 장소가 없습니다."));

        Place place = placeRepository.findById(next.getId().getPlaceId())
                .orElseThrow(() -> new NotFoundException("대안 장소를 찾을 수 없습니다."));

        String imageUrl = placeImageRepository
                .findFirstByPlaceIdAndPrimaryIsTrueOrderBySortOrderAsc(place.getId())
                .map(img -> img.getImageUrl())
                .orElse(null);

        return AlternativePlaceResponse.of(place, imageUrl);
    }
}
