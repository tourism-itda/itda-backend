package com.tourism.itda.planner.repository;

import com.tourism.itda.planner.entity.ItineraryTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItineraryTagRepository extends JpaRepository<ItineraryTag, Long> {

    List<ItineraryTag> findByItineraryId(Long itineraryId);

    void deleteByItineraryId(Long itineraryId);
}
