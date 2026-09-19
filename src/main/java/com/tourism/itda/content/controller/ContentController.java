package com.tourism.itda.content.controller;

import com.tourism.itda.content.dto.ContentDetailResponse;
import com.tourism.itda.content.dto.ContentListResponse;
import com.tourism.itda.content.dto.ContentPlaceListItemResponse;
import com.tourism.itda.content.dto.ContentResponse;
import com.tourism.itda.content.dto.TmdbCreditResponse;
import com.tourism.itda.content.service.ContentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(required = false) String kingdom,
            @RequestParam(name = "person_id", required = false) Long personId,
            @RequestParam(defaultValue = "recent") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int limit
    ) {
        return contentService.searchContents(
                q,
                mediaType,
                categoryId,
                kingdom,
                personId,
                sort,
                page,
                limit
        );
    }

    @PostMapping("/{contentId}")
    public ContentResponse save(
            @PathVariable Long contentId
    ) {
        return contentService.saveMovie(contentId);
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

    /**
     * media(타입/개봉연도) 백필 수동 트리거.
     * media 연결이 없는 기존 콘텐츠들을 TMDB 재호출 없이 Content 에 이미 저장된 값만으로 채운다.
     * 여러 번 실행해도 안전(이미 채워진 콘텐츠는 건너뜀).
     */
    @PostMapping("/backfill-media")
    public BackfillMediaResponse backfillMedia() {
        int filled = contentService.backfillMediaForExistingContents();
        return new BackfillMediaResponse(filled);
    }

    public record BackfillMediaResponse(int filled) {}

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
        Long userId = authentication != null
                ? (Long) authentication.getPrincipal()
                : null;

        return contentService.getRelatedPlaces(contentId, userId);
    }
}