package com.itda.content.repository;

import com.itda.content.domain.ContentPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContentPlaceRepository extends JpaRepository<ContentPlace, Long> {

    /** 추천 일정용: 콘텐츠의 장소를 recommend_order 순으로. */
    List<ContentPlace> findByContentIdOrderByRecommendOrderAsc(Long contentId);

    /** 대안 장소: 현재 순위(visitOrder) 다음 순위 중 exclude 제외한 첫 장소. */
    Optional<ContentPlace> findFirstByContentIdAndRecommendOrderGreaterThanAndPlaceIdNotOrderByRecommendOrderAsc(
            Long contentId, int visitOrder, Long excludePlaceId);

    /** exclude_place_id 가 없을 때. */
    Optional<ContentPlace> findFirstByContentIdAndRecommendOrderGreaterThanOrderByRecommendOrderAsc(
            Long contentId, int visitOrder);
}
