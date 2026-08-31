package com.tourism.itda.review.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ReviewLikeId implements Serializable {

    private Long reviewId;

    /** user 도메인 소유라 plain Long. */
    private Long userId;
}
