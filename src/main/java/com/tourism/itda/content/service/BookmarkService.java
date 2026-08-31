package com.tourism.itda.content.service;

import com.tourism.itda.content.dto.BookmarkCreateResponse;
import com.tourism.itda.content.dto.BookmarkListItemResponse;
import com.tourism.itda.content.entity.Bookmark;
import com.tourism.itda.content.exception.BookmarkAccessDeniedException;
import com.tourism.itda.content.exception.BookmarkAlreadyExistsException;
import com.tourism.itda.content.exception.BookmarkNotFoundException;
import com.tourism.itda.content.repository.BookmarkRepository;
import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceImage;
import com.tourism.itda.place.repository.PlaceImageRepository;
import com.tourism.itda.place.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PlaceRepository placeRepository;
    private final PlaceImageRepository placeImageRepository;

    public BookmarkService(
            BookmarkRepository bookmarkRepository,
            PlaceRepository placeRepository,
            PlaceImageRepository placeImageRepository
    ) {
        this.bookmarkRepository = bookmarkRepository;
        this.placeRepository = placeRepository;
        this.placeImageRepository = placeImageRepository;
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
        List<Bookmark> bookmarks = bookmarkRepository.findByUserIdOrderByCreatedAtDesc(userId);
        List<Long> placeIds = bookmarks.stream().map(Bookmark::getPlaceId).toList();

        Map<Long, Place> places = placeRepository.findAllById(placeIds).stream()
                .collect(Collectors.toMap(Place::getId, place -> place));
        Map<Long, String> primaryImageUrls = placeImageRepository.findByPlaceIdInAndPrimaryIsTrue(placeIds).stream()
                .collect(Collectors.toMap(PlaceImage::getPlaceId, PlaceImage::getImageUrl, (a, b) -> a));

        return bookmarks.stream()
                .map(bookmark -> BookmarkListItemResponse.from(
                        bookmark, places.get(bookmark.getPlaceId()), primaryImageUrls.get(bookmark.getPlaceId())))
                .toList();
    }
}
