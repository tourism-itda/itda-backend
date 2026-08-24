package com.tourism.itda.explore.controller;

import com.tourism.itda.explore.dto.CategoryDetailItem;
import com.tourism.itda.explore.dto.CategoryItem;
import com.tourism.itda.explore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    public List<CategoryItem> getCategories(
            @RequestParam String type
    ) {
        return categoryService.getCategories(type);
    }


    @GetMapping("/{categoryId}")
    public CategoryDetailItem getCategoryDetail(
            @PathVariable Long categoryId
    ) {
        return categoryService.getCategoryDetail(categoryId);
    }
}