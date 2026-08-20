package com.tourism.itda.planner.repository;

import com.tourism.itda.planner.entity.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
}
