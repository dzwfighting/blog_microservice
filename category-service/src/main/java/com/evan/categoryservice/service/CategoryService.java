package com.evan.categoryservice.service;

import com.evan.categoryservice.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public CategoryDTO saveCategory(CategoryDTO categoryDTO);
    public CategoryDTO getCategoryById(Long categoryId);
    public CategoryDTO getCategoryByName(String name);
    public List<CategoryDTO> getAllCategories();
    public CategoryDTO updateCategory(CategoryDTO categoryDTO);
    public String deleteCategoryById(Long categoryId);
}
