package com.tourism.itda.planner.repository;

import com.tourism.itda.planner.entity.ItineraryPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryPlaceRepository extends JpaRepository<ItineraryPlace, Long> {

    long countByItineraryId(Long itineraryId);
}
