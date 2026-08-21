package com.tourism.itda.review.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.tourism.itda.review.entity.Review;

import java.time.LocalDateTime;

/**
 * No.43 목록 항목 / No.44 작성 응답 공용.
 * isLiked 는 No.44 에서는 null 로 넘겨 응답에서 아예 빠지게 한다(NON_NULL).
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReviewResponse(
        Long reviewId,
        String authorNickname,
        String authorProfileUrl,
        Integer rating,
        String content,
        Integer likeCount,
        LocalDateTime createdAt,
        Boolean isLiked
) {
    public static ReviewResponse of(Review review, String authorNickname, String authorProfileUrl, Boolean isLiked) {
        return new ReviewResponse(
                review.getId(),
                authorNickname,
                authorProfileUrl,
                review.getRating(),
                review.getContentText(),
                review.getLikeCount(),
                review.getCreatedAt(),
                isLiked
        );
    }
}
