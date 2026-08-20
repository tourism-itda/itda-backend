package com.tourism.itda.planner.repository;

import com.tourism.itda.planner.entity.ContentSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentSpotRepository extends JpaRepository<ContentSpot, Long> {

    List<ContentSpot> findByContentIdOrderByRecommendOrderAsc(Long contentId);
}
