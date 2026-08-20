package com.tourism.itda.place.repository;

import com.tourism.itda.place.entity.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceImageRepository extends JpaRepository<PlaceImage, Long> {

    List<PlaceImage> findByPlaceIdOrderBySortOrderAsc(Long placeId);

    // 필드명이 isPrimary 라도 JavaBeans 프로퍼티명은 "primary" 로 해석된다 (getter: isPrimary()).
    List<PlaceImage> findByPlaceIdInAndPrimaryIsTrue(List<Long> placeIds);
}
