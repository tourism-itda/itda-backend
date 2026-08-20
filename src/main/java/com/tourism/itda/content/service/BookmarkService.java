package com.tourism.itda.content.service;

import com.tourism.itda.content.dto.BookmarkCreateResponse;
import com.tourism.itda.content.dto.BookmarkListItemResponse;
import com.tourism.itda.content.entity.Bookmark;
import com.tourism.itda.content.exception.BookmarkAccessDeniedException;
import com.tourism.itda.content.exception.BookmarkAlreadyExistsException;
import com.tourism.itda.content.exception.BookmarkNotFoundException;
import com.tourism.itda.content.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public BookmarkCreateResponse createBookmark(Long userId, Long placeId) {
        if (bookmarkRepository.existsByUserIdAndPlaceId(userId, placeId)) {
            throw new BookmarkAlreadyExistsException(placeId);
        }

        Bookmark bookmark = bookmarkRepository.save(new Bookmark(userId, placeId));
        return new BookmarkCreateResponse(bookmark.getId());
    }

    public void deleteBookmark(Long userId, Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new BookmarkNotFoundException(bookmarkId));

        if (!bookmark.getUserId().equals(userId)) {
            throw new BookmarkAccessDeniedException();
        }

        bookmarkRepository.delete(bookmark);
    }

    public List<BookmarkListItemResponse> getMyBookmarks(Long userId) {
        return bookmarkRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(BookmarkListItemResponse::from)
                .toList();
    }
}
