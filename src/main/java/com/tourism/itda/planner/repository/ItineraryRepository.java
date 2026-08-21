package com.tourism.itda.planner.repository;

import com.tourism.itda.planner.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    /** soft delete 되지 않은 일정만. */
    Optional<Itinerary> findByIdAndDeletedAtIsNull(Long id);

    List<Itinerary> findByUserIdAndDeletedAtIsNullOrderByCreatedAtDesc(Long userId);
}
