package com.tourism.itda.review.controller;

import com.tourism.itda.global.auth.LoginUser;
import com.tourism.itda.review.dto.ReviewCreateRequest;
import com.tourism.itda.review.dto.ReviewResponse;
import com.tourism.itda.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/itineraries/{itineraryId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /** No.43 GET — 인증 선택 (로그인 시에만 is_liked 계산). */
    @GetMapping
    public List<ReviewResponse> getReviews(
            @LoginUser(required = false) Long userId,
            @PathVariable Long itineraryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        return reviewService.getReviews(itineraryId, userId, page, limit);
    }

    /** No.44 POST — 인증 필요. */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse createReview(
            @LoginUser Long userId,
            @PathVariable Long itineraryId,
            @RequestBody ReviewCreateRequest request
    ) {
        return reviewService.createReview(userId, itineraryId, request);
    }
}
