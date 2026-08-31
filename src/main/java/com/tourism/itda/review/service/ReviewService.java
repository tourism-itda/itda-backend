package com.tourism.itda.review.service;

import com.tourism.itda.global.exception.InvalidRequestException;
import com.tourism.itda.global.exception.NotFoundException;
import com.tourism.itda.planner.entity.Itinerary;
import com.tourism.itda.planner.repository.ItineraryRepository;
import com.tourism.itda.review.dto.ReviewCreateRequest;
import com.tourism.itda.review.dto.ReviewLikeToggleResponse;
import com.tourism.itda.review.dto.ReviewResponse;
import com.tourism.itda.review.entity.Review;
import com.tourism.itda.review.entity.ReviewLike;
import com.tourism.itda.review.entity.ReviewLikeId;
import com.tourism.itda.review.repository.ReviewLikeRepository;
import com.tourism.itda.review.repository.ReviewRepository;
import com.tourism.itda.user.entity.User;
import com.tourism.itda.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final ItineraryRepository itineraryRepository;
    private final UserRepository userRepository;

    // =========================================================
    // No.43 리뷰 목록
    // =========================================================
    public List<ReviewResponse> getReviews(Long itineraryId, Long userId, int page, int limit) {
        requireSharedItinerary(itineraryId);

        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Review> reviews = reviewRepository.findByItineraryIdOrderByCreatedAtDesc(itineraryId, pageable);

        Map<Long, User> authorMap = findAuthors(reviews.getContent());

        return reviews.getContent().stream()
                .map(review -> {
                    User author = authorMap.get(review.getUserId());
                    boolean isLiked = userId != null
                            && reviewLikeRepository.existsById(new ReviewLikeId(review.getId(), userId));
                    return ReviewResponse.of(
                            review,
                            author != null ? author.getNickname() : null,
                            author != null ? author.getProfileUrl() : null,
                            isLiked);
                })
                .toList();
    }

    // =========================================================
    // No.44 리뷰 작성
    // =========================================================
    @Transactional
    public ReviewResponse createReview(Long userId, Long itineraryId, ReviewCreateRequest req) {
        requireSharedItinerary(itineraryId);

        if (req.rating() == null || req.rating() < 1 || req.rating() > 5) {
            throw new InvalidRequestException("rating은 1~5 사이여야 합니다.");
        }

        Review review = reviewRepository.save(
                new Review(userId, itineraryId, req.rating(), req.content()));

        User author = userRepository.findById(userId).orElse(null);

        return ReviewResponse.of(
                review,
                author != null ? author.getNickname() : null,
                author != null ? author.getProfileUrl() : null,
                null);
    }

    // =========================================================
    // No.45 리뷰 좋아요 토글
    // =========================================================
    @Transactional
    public ReviewLikeToggleResponse toggleLike(Long userId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("리뷰를 찾을 수 없습니다."));

        ReviewLikeId likeId = new ReviewLikeId(reviewId, userId);
        boolean liked;
        if (reviewLikeRepository.existsById(likeId)) {
            reviewLikeRepository.deleteById(likeId);
            liked = false;
        } else {
            reviewLikeRepository.save(new ReviewLike(review, userId));
            liked = true;
        }

        // like_count는 캐시 컬럼 — review_like 실제 건수로 매번 다시 맞춘다.
        int syncedCount = (int) reviewLikeRepository.countByReviewId(reviewId);
        review.changeLikeCount(syncedCount);

        return new ReviewLikeToggleResponse(liked, syncedCount);
    }

    // =========================================================
    // 내부 헬퍼
    // =========================================================
    private Itinerary requireSharedItinerary(Long itineraryId) {
        return itineraryRepository.findByIdAndSharedTrueAndDeletedAtIsNull(itineraryId)
                .orElseThrow(() -> new NotFoundException("공유된 일정을 찾을 수 없습니다."));
    }

    private Map<Long, User> findAuthors(List<Review> reviews) {
        List<Long> userIds = reviews.stream().map(Review::getUserId).distinct().toList();
        if (userIds.isEmpty()) return new java.util.HashMap<>();
        return userRepository.findAllById(userIds).stream()
                .collect(Collectors.toMap(User::getUserId, u -> u));
    }
}
