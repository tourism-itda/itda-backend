package com.tourism.itda.content.controller;

import com.tourism.itda.content.dto.ContentDetailResponse;
import com.tourism.itda.content.dto.ContentListResponse;
import com.tourism.itda.content.dto.ContentPlaceListItemResponse;
import com.tourism.itda.content.dto.ContentResponse;
import com.tourism.itda.content.dto.TmdbCreditResponse;
import com.tourism.itda.content.service.ContentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.tourism.itda.explore.enums.Kingdom;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public ContentListResponse list(
            @RequestParam(required = false) String q,
            @RequestParam(name = "media_type", required = false) String mediaType,
            @RequestParam(name = "category_id", required = false) Long categoryId,
            @RequestParam(defaultValue = "recent") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        return contentService.searchContents(q, mediaType, categoryId, sort, page, limit);
    }

    @PostMapping("/{movieId}")
    public ContentResponse save(
            @PathVariable Long movieId,
            @RequestParam Kingdom kingdom
    ) {
        return contentService.saveMovie(movieId, kingdom);
    }

    /**
     * 한국 역사 영화 자동 수집 수동 트리거.
     * 스케줄러(매일 새벽 4시)와 동일한 로직을 즉시 1회 실행한다.
     */
    @PostMapping("/collect")
    public CollectResponse collect(
            @RequestParam(defaultValue = "10") int limit
    ) {
        int saved = contentService.collectKoreanHistoryMovies(limit);
        return new CollectResponse(saved);
    }

    public record CollectResponse(int saved) {}

    @GetMapping("/credits/{movieId}")
    public TmdbCreditResponse credits(
            @PathVariable Long movieId
    ) {
        return contentService.getCredits(movieId);
    }

    @GetMapping("/{id}")
    public ContentDetailResponse find(
            @PathVariable Long id
    ) {
        return contentService.findContent(id);
    }

    @GetMapping("/{contentId}/places")
    public List<ContentPlaceListItemResponse> getRelatedPlaces(
            Authentication authentication,
            @PathVariable Long contentId
    ) {
        Long userId = authentication != null ? (Long) authentication.getPrincipal() : null;
        return contentService.getRelatedPlaces(contentId, userId);
    }
}