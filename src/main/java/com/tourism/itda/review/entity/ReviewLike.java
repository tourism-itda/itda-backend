package com.tourism.itda.review.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ReviewLike {

    @EmbeddedId
    private ReviewLikeId id;

    /** review는 같은 review 도메인 소유라 실제 연관관계로 연결. */
    @MapsId("reviewId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public ReviewLike(Review review, Long userId) {
        this.review = review;
        this.id = new ReviewLikeId(review.getId(), userId);
    }
}
