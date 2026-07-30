package com.tourism.itda.content.controller;

import com.tourism.itda.content.client.TmdbClient;
import com.tourism.itda.content.dto.ContentResponseDto;
import com.tourism.itda.content.dto.TmdbCreditResponseDto;
import com.tourism.itda.content.service.ContentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    private final ContentService contentService;
    private final TmdbClient tmdbClient;

    public ContentController(
            ContentService contentService,
            TmdbClient tmdbClient
    ) {
        this.contentService = contentService;
        this.tmdbClient = tmdbClient;
    }

    @PostMapping("/{movieId}")
    public ContentResponseDto save(
            @PathVariable Long movieId
    ) {
        return contentService.saveMovie(movieId);
    }

    @GetMapping("/credits/{movieId}")
    public TmdbCreditResponseDto credits(
            @PathVariable Long movieId
    ) {
        return tmdbClient.getCredits(movieId);
    }

    @GetMapping("/{id}")
    public ContentResponseDto find(
            @PathVariable Long id
    ) {
        return contentService.findContent(id);
    }
}