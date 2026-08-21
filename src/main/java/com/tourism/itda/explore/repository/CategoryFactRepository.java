package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.CategoryFact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryFactRepository
        extends JpaRepository<CategoryFact, Long> {

    List<CategoryFact> findByCategoryCategoryIdOrderByFactOrder(Long categoryId);

}