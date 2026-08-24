package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.PlacePerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacePersonRepository
        extends JpaRepository<PlacePerson, PlacePerson.PlacePersonId> {
}