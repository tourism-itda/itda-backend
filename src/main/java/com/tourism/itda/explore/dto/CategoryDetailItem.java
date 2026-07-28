package com.tourism.itda.explore.dto;

import com.tourism.itda.explore.entity.Category;

import java.util.List;

public record CategoryDetailItem(
        Long categoryId,
        String type,
        String name,
        String years,
        String description,
        String imageUrl,
        List<CategoryFactItem> facts
) {

    public static CategoryDetailItem from(Category category) {

        return new CategoryDetailItem(
                category.getCategoryId(),
                category.getType(),
                category.getName(),
                category.getYears(),
                category.getDescription(),
                category.getImageUrl(),
                category.getFacts()
                        .stream()
                        .map(CategoryFactItem::from)
                        .toList()
        );
    }
}