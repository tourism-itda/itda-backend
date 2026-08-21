package com.tourism.itda.review.repository;

import com.tourism.itda.review.entity.ReviewLike;
import com.tourism.itda.review.entity.ReviewLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, ReviewLikeId> {

}
