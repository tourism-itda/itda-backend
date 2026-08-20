package com.tourism.itda.content.repository;

import com.tourism.itda.content.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    boolean existsByUserIdAndPlaceId(Long userId, Long placeId);

    List<Bookmark> findByUserIdOrderByCreatedAtDesc(Long userId);

    Optional<Bookmark> findByIdAndUserId(Long id, Long userId);
}
