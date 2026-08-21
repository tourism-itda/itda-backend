package com.tourism.itda.explore.dto;

import com.tourism.itda.explore.entity.Category;

public record CategoryItem(
        Long categoryId,
        String type,
        String name,
        String years,
        String imageUrl
) {

    public static CategoryItem from(Category category) {
        return new CategoryItem(
                category.getCategoryId(),
                category.getType(),
                category.getName(),
                category.getYears(),
                category.getImageUrl()
        );
    }
}