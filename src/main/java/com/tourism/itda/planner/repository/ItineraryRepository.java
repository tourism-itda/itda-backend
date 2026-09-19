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

    /**
     * No.40 커뮤니티 목록용 — 공유 중이고 soft delete 안 된 일정만, 최신순.
     * 검색어는 일정 제목뿐 아니라 그 일정이 만들어진 작품(content) 제목에도 매칭한다 —
     * 작품 이름으로 검색하면 그 작품으로 만든 루트가 나오도록. content_id 가 null 인 일정은
     * IN 서브쿼리에서 자연히 빠지므로 제목 매칭만 적용된다.
     */
    @Query("SELECT i FROM Itinerary i WHERE i.shared = true AND i.deletedAt IS NULL "
            + "AND (:likePattern IS NULL OR i.title LIKE :likePattern "
            + "OR i.contentId IN (SELECT c.id FROM Content c WHERE c.title LIKE :likePattern)) "
            + "ORDER BY i.createdAt DESC")
    List<Itinerary> findSharedByKeywordOrderByCreatedAtDesc(@Param("likePattern") String likePattern);

    /** No.41 커뮤니티 상세 — 공유 중이고 soft delete 안 된 것만 조회 가능. */
    Optional<Itinerary> findByIdAndSharedTrueAndDeletedAtIsNull(Long id);

    /** No.42 가져오기 중복 방지 — 같은 유저가 같은 원본을 이미 가져왔는지(soft delete 제외) 확인. */
    boolean existsByUserIdAndSourceItineraryIdAndDeletedAtIsNull(Long userId, Long sourceItineraryId);
}
