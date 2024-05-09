package com.evan.categoryservice.service.impl;

import com.evan.categoryservice.dto.CategoryDTO;
import com.evan.categoryservice.entity.Category;
import com.evan.categoryservice.mapper.CategoryMapper;
import com.evan.categoryservice.repository.CategoryRepository;
import com.evan.categoryservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.mapToCategory(categoryDTO);
        Category newCategory = categoryRepository.save(category);
        CategoryDTO newCategoryDTO = CategoryMapper.mapToCategoryDto(newCategory);
        return newCategoryDTO;
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        CategoryDTO categoryDTO = CategoryMapper.mapToCategoryDto(category);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getCategoriesByName(String name) {
        List<Category> categories = categoryRepository.findCategoryByName(name);
        List<CategoryDTO> categoriesDTO = categories.stream().map(CategoryMapper::mapToCategoryDto).collect(Collectors.toList());
        return categoriesDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTO = categories.stream().map(CategoryMapper::mapToCategoryDto).collect(Collectors.toList());
        return categoriesDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.mapToCategory(categoryDTO);
        Category newCategory = categoryRepository.save(category);
        CategoryDTO newCategoryDto = CategoryMapper.mapToCategoryDto(newCategory);
        return newCategoryDto;
    }

    @Override
    public String deleteCategoryById(Long categoryId) {
        Category delCategory = categoryRepository.findById(categoryId).get();
        categoryRepository.delete(delCategory);
        return "delete success";
    }
}
