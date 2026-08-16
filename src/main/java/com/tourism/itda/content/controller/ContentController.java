package com.tourism.itda.content.controller;

import com.tourism.itda.content.dto.ContentResponse;
import com.tourism.itda.content.dto.TmdbCreditResponse;
import com.tourism.itda.content.service.ContentService;
import org.springframework.web.bind.annotation.*;

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
    public ContentResponse find(
            @PathVariable Long id
    ) {
        return contentService.findContent(id);
    }
}