package com.evan.categoryservice.service.impl;

import com.evan.categoryservice.dto.CategoryDTO;
import com.evan.categoryservice.entity.Category;
import com.evan.categoryservice.exception.ResourceNotFoundException;
import com.evan.categoryservice.mapper.CategoryMapper;
import com.evan.categoryservice.repository.CategoryRepository;
import com.evan.categoryservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        LOGGER.info("in saveCategory method");
        Category category = CategoryMapper.mapToCategory(categoryDTO);
        Category newCategory = categoryRepository.save(category);
        CategoryDTO newCategoryDTO = CategoryMapper.mapToCategoryDto(newCategory);
        return newCategoryDTO;
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        LOGGER.info("in getCategoryById");
        Category category = categoryRepository.findById(categoryId).get();
        if (category == null) throw new ResourceNotFoundException("Category", "id", categoryId);
        CategoryDTO categoryDTO = CategoryMapper.mapToCategoryDto(category);
        return categoryDTO;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        LOGGER.info("In getCategoryByName, the name is: " + name);
        Category category = categoryRepository.findCategoryByName(name);
        if (category == null) throw new ResourceNotFoundException("Category", "name", name);
        CategoryDTO categoryDTO = CategoryMapper.mapToCategoryDto(category);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        LOGGER.info("in getAllCategories");
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoriesDTO = categories.stream().map(CategoryMapper::mapToCategoryDto).collect(Collectors.toList());
        return categoriesDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        LOGGER.info("in updateCategory, will update this information: " + categoryDTO.getName());
        Category category = categoryRepository.findCategoryByName(categoryDTO.getName());
        if (category == null) throw new ResourceNotFoundException("Category", "name", categoryDTO.getName());
        Category convertToCategory = CategoryMapper.mapToCategory(categoryDTO);

        category.setName(convertToCategory.getName());
        category.setDescription(convertToCategory.getDescription());
        category.setPostIds(convertToCategory.getPostIds());

        Category newCategory = categoryRepository.save(category);
        CategoryDTO newCategoryDto = CategoryMapper.mapToCategoryDto(newCategory);
        return newCategoryDto;
    }

    @Override
    public String deleteCategoryById(Long categoryId) {
        Category delCategory = categoryRepository.findById(categoryId).get();
        if (delCategory == null) throw new ResourceNotFoundException("Category", "id", categoryId);
        categoryRepository.delete(delCategory);
        return "delete success";
    }
}
