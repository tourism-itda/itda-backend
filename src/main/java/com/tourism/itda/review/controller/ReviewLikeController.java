package com.tourism.itda.review.controller;

import com.tourism.itda.global.auth.LoginUser;
import com.tourism.itda.review.dto.ReviewLikeToggleResponse;
import com.tourism.itda.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewLikeController {

    private final ReviewService reviewService;

    /** No.45 POST /reviews/:review_id/likes — 인증 필요, 토글. */
    @PostMapping("/{reviewId}/likes")
    public ReviewLikeToggleResponse toggleLike(
            @LoginUser Long userId,
            @PathVariable Long reviewId
    ) {
        return reviewService.toggleLike(userId, reviewId);
    }
}
