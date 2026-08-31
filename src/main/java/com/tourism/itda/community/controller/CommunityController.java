package com.tourism.itda.community.controller;

import com.tourism.itda.community.dto.CommunityPostDetailResponse;
import com.tourism.itda.community.dto.CommunityPostSummaryResponse;
import com.tourism.itda.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/community/posts")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    /** No.40 GET /api/community/posts — 인증 불필요. */
    @GetMapping
    public List<CommunityPostSummaryResponse> getPosts(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "recent") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        return communityService.getPosts(q, sort, page, limit);
    }

    /** No.41 GET /api/community/posts/:itinerary_id — 인증 불필요. */
    @GetMapping("/{itineraryId}")
    public CommunityPostDetailResponse getPostDetail(@PathVariable Long itineraryId) {
        return communityService.getPostDetail(itineraryId);
    }
}
