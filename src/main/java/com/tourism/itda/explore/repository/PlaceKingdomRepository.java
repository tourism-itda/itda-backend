package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.PlaceKingdom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceKingdomRepository
        extends JpaRepository<PlaceKingdom, PlaceKingdom.PlaceKingdomId> {
}