package com.itda.itinerary.repository;

import com.itda.itinerary.domain.ItineraryPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryPlaceRepository extends JpaRepository<ItineraryPlace, Long> {

    long countByItineraryId(Long itineraryId);
}
