package com.tourism.itda.bookmark.repository;

import com.tourism.itda.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    boolean existsByUserIdAndPlaceId(Long userId, Long placeId);
}
