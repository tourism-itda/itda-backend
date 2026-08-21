package com.tourism.itda.place.repository;

import com.tourism.itda.place.entity.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PlaceImageRepository extends JpaRepository<PlaceImage, Long> {

    List<PlaceImage> findByPlaceIdOrderBySortOrderAsc(Long placeId);

    Optional<PlaceImage> findFirstByPlaceIdAndPrimaryIsTrueOrderBySortOrderAsc(Long placeId);

    /** 여러 장소의 대표 이미지 일괄 조회 (일정 목록/상세용). */
    List<PlaceImage> findByPlaceIdInAndPrimaryIsTrue(Collection<Long> placeIds);
}
