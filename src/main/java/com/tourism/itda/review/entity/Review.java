package com.tourism.itda.review.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    /** planner 도메인 소유가 아니라 다른 도메인(user) 소유라 plain Long. */
    @Column(name = "user_id")
    private Long userId;

    /** planner 도메인 소유라 plain Long. */
    @Column(name = "itinerary_id")
    private Long itineraryId;

    private Integer rating;

    /** "content" 테이블(콘텐츠 도메인)과 이름 충돌 방지를 위해 content_text로 명명. */
    @Column(name = "content_text", columnDefinition = "TEXT")
    private String contentText;

    @Column(name = "like_count")
    private Integer likeCount = 0;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Review(Long userId, Long itineraryId, Integer rating, String contentText) {
        this.userId = userId;
        this.itineraryId = itineraryId;
        this.rating = rating;
        this.contentText = contentText;
    }

    public void changeRating(Integer rating) {
        this.rating = rating;
    }

    public void changeContentText(String contentText) {
        this.contentText = contentText;
    }

    public void changeLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
