package com.tourism.itda.explore.dto;

import com.tourism.itda.explore.entity.CategoryFact;

public record CategoryFactItem(
        Long categoryFactId,
        String factText,
        Integer factOrder
) {

    public static CategoryFactItem from(CategoryFact fact) {
        return new CategoryFactItem(
                fact.getCategoryFactId(),
                fact.getFactText(),
                fact.getFactOrder()
        );
    }
}