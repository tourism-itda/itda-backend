package com.tourism.itda.explore.repository;

import com.tourism.itda.explore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByType(String type);

    List<Category> findByParentCategoryId(Long parentId);
}