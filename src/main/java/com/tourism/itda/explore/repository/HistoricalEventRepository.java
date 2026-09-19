package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.HistoricalEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistoricalEventRepository extends JpaRepository<HistoricalEvent, Long> {

    Optional<HistoricalEvent> findByName(String name);
}
