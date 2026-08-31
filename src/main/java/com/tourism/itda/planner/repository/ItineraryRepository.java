package com.tourism.itda.planner.repository;

import com.tourism.itda.planner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    /** soft delete 되지 않은 일정만. */
    Optional<Itinerary> findByIdAndDeletedAtIsNull(Long id);

    List<Itinerary> findByUserIdAndDeletedAtIsNullOrderByCreatedAtDesc(Long userId);

    /** No.40 커뮤니티 목록용 — 공유 중이고 soft delete 안 된 일정만, 최신순. */
    @Query("SELECT i FROM Itinerary i WHERE i.shared = true AND i.deletedAt IS NULL "
            + "AND (:likePattern IS NULL OR i.title LIKE :likePattern) "
            + "ORDER BY i.createdAt DESC")
    List<Itinerary> findSharedByTitleLikeOrderByCreatedAtDesc(@Param("likePattern") String likePattern);

    /** No.41 커뮤니티 상세 — 공유 중이고 soft delete 안 된 것만 조회 가능. */
    Optional<Itinerary> findByIdAndSharedTrueAndDeletedAtIsNull(Long id);
}
