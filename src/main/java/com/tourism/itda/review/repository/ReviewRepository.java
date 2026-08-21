package com.tourism.itda.review.repository;

import com.tourism.itda.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    long countByItineraryId(Long itineraryId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.itineraryId = :itineraryId")
    Double findAverageRatingByItineraryId(@Param("itineraryId") Long itineraryId);

    /** No.43 리뷰 목록 — 최신순, 페이지네이션. */
    Page<Review> findByItineraryIdOrderByCreatedAtDesc(Long itineraryId, Pageable pageable);
}
