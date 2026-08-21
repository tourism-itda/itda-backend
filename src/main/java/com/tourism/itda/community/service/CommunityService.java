package com.tourism.itda.community.service;

import com.tourism.itda.community.dto.CommunityPostDetailResponse;
import com.tourism.itda.community.dto.CommunityPostSummaryResponse;
import com.tourism.itda.community.dto.CommunityStopView;
import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.repository.ContentRepository;
import com.tourism.itda.global.exception.NotFoundException;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceImage;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
import com.tourism.itda.planner.entity.Itinerary;
import com.tourism.itda.planner.entity.ItineraryPlace;
import com.tourism.itda.planner.entity.ItineraryTag;
import com.tourism.itda.planner.repository.ItineraryPlaceRepository;
import com.tourism.itda.planner.repository.ItineraryRepository;
import com.tourism.itda.planner.repository.ItineraryTagRepository;
import com.tourism.itda.review.repository.ReviewRepository;
import com.tourism.itda.user.entity.User;
import com.tourism.itda.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {

    private final ItineraryRepository itineraryRepository;
    private final ItineraryPlaceRepository itineraryPlaceRepository;
    private final ItineraryTagRepository itineraryTagRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;

    // =========================================================
    // No.40 커뮤니티 목록
    // =========================================================
    public List<CommunityPostSummaryResponse> getPosts(String q, String sort, int page, int limit) {
        String likePattern = (q != null && !q.isBlank()) ? "%" + q + "%" : null;

        // createdAt DESC 로 미리 정렬된 상태로 가져온다 (sort=recent 는 그대로 사용).
        List<Itinerary> itineraries = itineraryRepository.findSharedByTitleLikeOrderByCreatedAtDesc(likePattern);

        // 정렬 기준(popular/rating)이 리뷰 집계값이라 Itinerary 컬럼만으로는 DB 정렬이 안 된다.
        // 응답에도 어차피 필요한 값이라 미리 다 계산해두고 그 값으로 재정렬한다.
        // ⚠️ Collectors.toMap 은 값이 null이면 내부적으로 Map.merge 를 써서 NPE가 난다 —
        // 리뷰가 없는 일정은 평균 평점이 null이라 직접 HashMap 에 채운다.
        Map<Long, Long> reviewCounts = new java.util.HashMap<>();
        Map<Long, Double> avgRatings = new java.util.HashMap<>();
        for (Itinerary it : itineraries) {
            reviewCounts.put(it.getId(), reviewRepository.countByItineraryId(it.getId()));
            avgRatings.put(it.getId(), reviewRepository.findAverageRatingByItineraryId(it.getId()));
        }

        List<Itinerary> sorted = sortForCommunity(itineraries, sort, reviewCounts, avgRatings);
        List<Itinerary> pageItems = paginate(sorted, page, limit);

        Map<Long, User> authorMap = findAuthors(pageItems);
        Map<Long, Content> contentMap = findContents(pageItems);

        return pageItems.stream()
                .map(it -> {
                    User author = authorMap.get(it.getUserId());
                    List<String> tags = tagNames(it.getId());
                    return new CommunityPostSummaryResponse(
                            it.getId(),
                            it.getTitle(),
                            author != null ? author.getNickname() : null,
                            author != null ? author.getProfileUrl() : null,
                            avgRatings.get(it.getId()),
                            reviewCounts.getOrDefault(it.getId(), 0L),
                            itineraryPlaceRepository.countByItineraryId(it.getId()),
                            it.getRegion(),
                            it.getDurationLabel(),
                            resolveThumbnail(it, contentMap.get(it.getContentId())),
                            tags
                    );
                })
                .toList();
    }

    // =========================================================
    // No.41 커뮤니티 상세
    // =========================================================
    public CommunityPostDetailResponse getPostDetail(Long itineraryId) {
        Itinerary itinerary = itineraryRepository.findByIdAndSharedTrueAndDeletedAtIsNull(itineraryId)
                .orElseThrow(() -> new NotFoundException("공유된 일정을 찾을 수 없습니다."));

        User author = userRepository.findById(itinerary.getUserId()).orElse(null);

        List<ItineraryPlace> places = itineraryPlaceRepository
                .findByItineraryIdOrderByDayNumberAscVisitOrderAsc(itineraryId);
        List<Long> placeIds = places.stream().map(ItineraryPlace::getPlaceId).toList();

        Map<Long, Place> placeMap = placeMap(placeIds);
        Map<Long, String> imageMap = primaryImageMap(placeIds);

        List<CommunityStopView> stops = places.stream()
                .map(ip -> {
                    Place p = placeMap.get(ip.getPlaceId());
                    return new CommunityStopView(
                            ip.getId(),
                            ip.getVisitOrder(),
                            p != null ? p.getName() : null,
                            p != null ? p.getCategory() : null,
                            imageMap.get(ip.getPlaceId()),
                            p != null ? p.getDescription() : null,
                            p != null ? p.getAddress() : null,
                            p != null ? p.getOpeningHours() : null,
                            p != null ? p.getLatitude() : 0,
                            p != null ? p.getLongitude() : 0);
                })
                .toList();

        Content content = itinerary.getContentId() == null ? null
                : contentRepository.findById(itinerary.getContentId()).orElse(null);
        String thumbnailUrl = resolveThumbnail(itinerary, content, places, placeMap);

        return new CommunityPostDetailResponse(
                itinerary.getId(),
                itinerary.getTitle(),
                itinerary.getDescription(),
                new CommunityPostDetailResponse.AuthorView(
                        author != null ? author.getNickname() : null,
                        author != null ? author.getProfileUrl() : null),
                reviewRepository.findAverageRatingByItineraryId(itineraryId),
                reviewRepository.countByItineraryId(itineraryId),
                places.size(),
                itinerary.getRegion(),
                itinerary.getDurationLabel(),
                tagNames(itineraryId),
                thumbnailUrl,
                stops
        );
    }

    // =========================================================
    // 내부 헬퍼
    // =========================================================
    private List<Itinerary> sortForCommunity(List<Itinerary> itineraries, String sort,
                                              Map<Long, Long> reviewCounts, Map<Long, Double> avgRatings) {
        List<Itinerary> sorted = new java.util.ArrayList<>(itineraries);
        if ("popular".equalsIgnoreCase(sort)) {
            sorted.sort(Comparator.comparing(
                    (Itinerary it) -> reviewCounts.getOrDefault(it.getId(), 0L), Comparator.reverseOrder()));
        } else if ("rating".equalsIgnoreCase(sort)) {
            sorted.sort((a, b) -> {
                Double ra = avgRatings.get(a.getId());
                Double rb = avgRatings.get(b.getId());
                if (ra == null && rb == null) return 0;
                if (ra == null) return 1;   // 평점 없는 항목은 뒤로
                if (rb == null) return -1;
                return Double.compare(rb, ra);
            });
        }
        // sort=recent(기본값)는 이미 createdAt DESC 로 가져온 순서를 그대로 사용.
        return sorted;
    }

    private List<Itinerary> paginate(List<Itinerary> sorted, int page, int limit) {
        int from = Math.min(page * limit, sorted.size());
        int to = Math.min(from + limit, sorted.size());
        return sorted.subList(from, to);
    }

    private Map<Long, User> findAuthors(List<Itinerary> itineraries) {
        List<Long> userIds = itineraries.stream().map(Itinerary::getUserId).distinct().toList();
        if (userIds.isEmpty()) return Map.of();
        return userRepository.findAllById(userIds).stream()
                .collect(Collectors.toMap(User::getUserId, u -> u));
    }

    private Map<Long, Content> findContents(List<Itinerary> itineraries) {
        List<Long> contentIds = itineraries.stream()
                .map(Itinerary::getContentId).filter(java.util.Objects::nonNull).distinct().toList();
        // ⚠️ Map.of()(불변 빈 맵)는 get(null) 호출 시 NPE를 던진다.
        // itinerary.getContentId() 가 null 인 경우가 흔해서 반드시 null-key 조회에 안전한 HashMap 을 써야 한다.
        if (contentIds.isEmpty()) return new java.util.HashMap<>();
        return contentRepository.findAllById(contentIds).stream()
                .collect(Collectors.toMap(Content::getId, c -> c));
    }

    private List<String> tagNames(Long itineraryId) {
        return itineraryTagRepository.findByItineraryId(itineraryId).stream()
                .map(ItineraryTag::getTagName)
                .toList();
    }

    private Map<Long, Place> placeMap(List<Long> placeIds) {
        if (placeIds.isEmpty()) return Map.of();
        return placeRepository.findAllById(placeIds).stream()
                .collect(Collectors.toMap(Place::getId, p -> p, (a, b) -> a));
    }

    private Map<Long, String> primaryImageMap(List<Long> placeIds) {
        if (placeIds.isEmpty()) return Map.of();
        return placeImageRepository.findByPlaceIdInAndPrimaryIsTrue(placeIds).stream()
                .collect(Collectors.toMap(PlaceImage::getPlaceId, PlaceImage::getImageUrl, (a, b) -> a));
    }

    /** 목록용: content 썸네일 우선, 없으면 첫 스톱 장소의 대표이미지로 폴백. */
    private String resolveThumbnail(Itinerary itinerary, Content content) {
        if (content != null && content.getThumbnailUrl() != null) {
            return content.getThumbnailUrl();
        }
        return itineraryPlaceRepository.findFirstByItineraryIdOrderByDayNumberAscVisitOrderAsc(itinerary.getId())
                .map(ItineraryPlace::getPlaceId)
                .flatMap(placeImageRepository::findFirstByPlaceIdAndPrimaryIsTrueOrderBySortOrderAsc)
                .map(PlaceImage::getImageUrl)
                .orElse(null);
    }

    /** 상세용: 이미 조회해 둔 places/placeMap 을 재사용해 중복 쿼리를 피한다. */
    private String resolveThumbnail(Itinerary itinerary, Content content,
                                     List<ItineraryPlace> places, Map<Long, Place> placeMap) {
        if (content != null && content.getThumbnailUrl() != null) {
            return content.getThumbnailUrl();
        }
        if (places.isEmpty()) return null;
        Long firstPlaceId = places.get(0).getPlaceId();
        return placeImageRepository.findFirstByPlaceIdAndPrimaryIsTrueOrderBySortOrderAsc(firstPlaceId)
                .map(PlaceImage::getImageUrl)
                .orElse(null);
    }
}
