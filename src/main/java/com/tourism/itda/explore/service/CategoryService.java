package com.tourism.itda.explore.service;

import com.tourism.itda.explore.dto.CategoryDetailItem;
import com.tourism.itda.explore.dto.CategoryItem;
import com.tourism.itda.explore.entity.Category;
import com.tourism.itda.explore.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;


    // GET /categories?type=ERA
    public List<CategoryItem> getCategories(String type) {

        return categoryRepository.findByType(type)
                .stream()
                .map(CategoryItem::from)
                .toList();
    }


    // GET /categories/{categoryId}
    public CategoryDetailItem getCategoryDetail(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new IllegalArgumentException("카테고리를 찾을 수 없습니다.")
                );

        return CategoryDetailItem.from(category);
    }
}