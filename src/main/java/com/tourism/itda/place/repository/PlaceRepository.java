package com.tourism.itda.place.repository;

import com.tourism.itda.place.entity.Place;
import com.tourism.itda.place.entity.PlaceSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findBySourceAndExternalId(
            PlaceSource source,
            String externalId
    );

    Optional<Place> findByName(String name);
}