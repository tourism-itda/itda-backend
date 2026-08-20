package com.tourism.itda.content.controller;

import com.tourism.itda.content.dto.BookmarkCreateRequest;
import com.tourism.itda.content.dto.BookmarkCreateResponse;
import com.tourism.itda.content.dto.BookmarkDeleteResponse;
import com.tourism.itda.content.dto.BookmarkListItemResponse;
import com.tourism.itda.content.service.BookmarkService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping
    public BookmarkCreateResponse create(
            Authentication authentication,
            @RequestBody BookmarkCreateRequest request
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return bookmarkService.createBookmark(userId, request.placeId());
    }

    @DeleteMapping("/{bookmarkId}")
    public BookmarkDeleteResponse delete(
            Authentication authentication,
            @PathVariable Long bookmarkId
    ) {
        Long userId = (Long) authentication.getPrincipal();
        bookmarkService.deleteBookmark(userId, bookmarkId);
        return BookmarkDeleteResponse.ok();
    }

    @GetMapping
    public List<BookmarkListItemResponse> getMyBookmarks(
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return bookmarkService.getMyBookmarks(userId);
    }
}
