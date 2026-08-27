package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.PlaceKingdom;
import com.tourism.itda.explore.enums.Kingdom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceKingdomRepository
        extends JpaRepository<PlaceKingdom, PlaceKingdom.PlaceKingdomId> {

    List<PlaceKingdom> findByKingdom(Kingdom kingdom);
}