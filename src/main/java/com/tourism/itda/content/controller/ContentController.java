package com.tourism.itda.content.controller;

import com.tourism.itda.content.dto.ContentDetailResponse;
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

    @PostMapping("/{movieId}")
    public ContentResponse save(
            @PathVariable Long movieId
    ) {
        return contentService.saveMovie(movieId);
    }

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