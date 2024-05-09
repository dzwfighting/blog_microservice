package com.evan.categoryservice.mapper;

import com.evan.categoryservice.dto.CategoryDTO;
import com.evan.categoryservice.entity.Category;

public class CategoryMapper {
    public static CategoryDTO mapToCategoryDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription(),
                category.getPostIds()
        );
        return categoryDTO;
    }

    public static Category mapToCategory(CategoryDTO categoryDTO) {
        Category category = new Category(
                categoryDTO.getCategoryId(),
                categoryDTO.getName(),
                categoryDTO.getDescription(),
                categoryDTO.getPostIds()
        );
        return category;
    }
}
