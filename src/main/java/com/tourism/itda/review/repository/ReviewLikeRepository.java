package com.tourism.itda.review.repository;

import com.tourism.itda.review.entity.ReviewLike;
import com.tourism.itda.review.entity.ReviewLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, ReviewLikeId> {

    /** review_like 실제 건수 — review.like_count 캐시 동기화용. */
    long countByReviewId(Long reviewId);

    /** 리뷰 삭제 시 연관 좋아요를 벌크 제거(FK 제약 해소). */
    @Modifying
    @Query("delete from ReviewLike rl where rl.id.reviewId = :reviewId")
    void deleteByReviewId(@Param("reviewId") Long reviewId);
}
