package com.itda.place.service;

import com.itda.bookmark.repository.BookmarkRepository;
import com.itda.common.exception.NotFoundException;
import com.itda.content.domain.ContentPlace;
import com.itda.content.repository.ContentPlaceRepository;
import com.itda.place.domain.Place;
import com.itda.place.dto.AlternativePlaceResponse;
import com.itda.place.dto.PlaceDetailResponse;
import com.itda.place.dto.PlaceImageDto;
import com.itda.place.repository.PlaceImageRepository;
import com.itda.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;
    private final ContentPlaceRepository contentPlaceRepository;
    private final BookmarkRepository bookmarkRepository;

    /** No.25 장소 상세 (v4, 인증 선택). userId 가 null 이면 비로그인 → is_bookmarked=false. */
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
                    .findFirstByContentIdAndRecommendOrderGreaterThanAndPlaceIdNotOrderByRecommendOrderAsc(
                            contentId, visitOrder, excludePlaceId)
                : contentPlaceRepository
                    .findFirstByContentIdAndRecommendOrderGreaterThanOrderByRecommendOrderAsc(
                            contentId, visitOrder))
                .orElseThrow(() -> new NotFoundException("더 이상 추천할 대안 장소가 없습니다."));

        Place place = placeRepository.findById(next.getPlaceId())
                .orElseThrow(() -> new NotFoundException("대안 장소를 찾을 수 없습니다."));

        String imageUrl = placeImageRepository
                .findFirstByPlaceIdAndPrimaryIsTrueOrderBySortOrderAsc(place.getId())
                .map(img -> img.getImageUrl())
                .orElse(null);

        return AlternativePlaceResponse.of(place, imageUrl);
    }
}
