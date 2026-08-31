package com.tourism.itda.planner.repository;

import com.tourism.itda.planner.entity.ItineraryPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItineraryPlaceRepository extends JpaRepository<ItineraryPlace, Long> {

    long countByItineraryId(Long itineraryId);

    List<ItineraryPlace> findByItineraryIdOrderByDayNumberAscVisitOrderAsc(Long itineraryId);

    Optional<ItineraryPlace> findFirstByItineraryIdOrderByDayNumberAscVisitOrderAsc(Long itineraryId);
}
