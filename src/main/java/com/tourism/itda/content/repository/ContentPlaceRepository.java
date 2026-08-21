package com.tourism.itda.content.repository;

import com.tourism.itda.content.entity.Content;
import com.tourism.itda.content.entity.ContentPlace;
import com.tourism.itda.content.entity.ContentPlaceId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContentPlaceRepository extends JpaRepository<ContentPlace, ContentPlaceId> {

    List<ContentPlace> findByContentOrderByRecommendOrderAsc(Content content);

    // --- 아래는 장소/일정 파트(권승훈)에서 추가 ---
    // ContentPlace 의 PK 가 @EmbeddedId(ContentPlaceId) 라서 content_id / place_id 는
    // 'id.contentId' / 'id.placeId' 프로퍼티 경로로 접근한다 (메서드명의 IdContentId / IdPlaceId).

    /** 추천 일정·루트 생성용 — 콘텐츠의 촬영지를 recommend_order 오름차순으로. */
    List<ContentPlace> findByIdContentIdOrderByRecommendOrderAsc(Long contentId);

    /** No.26 '다른 곳 추천' — 지금 순번 다음으로 추천 순위가 높은 장소 1건. */
    Optional<ContentPlace> findFirstByIdContentIdAndRecommendOrderGreaterThanOrderByRecommendOrderAsc(
            Long contentId, Integer recommendOrder);

    /** No.26 '다른 곳 추천' — 특정 장소를 제외하고 다음 순위 1건. */
    Optional<ContentPlace> findFirstByIdContentIdAndRecommendOrderGreaterThanAndIdPlaceIdNotOrderByRecommendOrderAsc(
            Long contentId, Integer recommendOrder, Long excludePlaceId);
}
