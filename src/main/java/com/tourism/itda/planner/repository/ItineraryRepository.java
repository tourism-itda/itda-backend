package com.tourism.itda.planner.repository;

import com.tourism.itda.planner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
