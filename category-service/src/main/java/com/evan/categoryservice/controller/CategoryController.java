package com.evan.categoryservice.controller;

import com.evan.categoryservice.dto.CategoryDTO;
import com.evan.categoryservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO saveCategoryDTO = categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(saveCategoryDTO, HttpStatus.CREATED);
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long categoryId) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByName(@PathVariable String name){
        List<CategoryDTO> categoriesDTO = categoryService.getCategoriesByName(name);
        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoriesDTO = categoryService.getAllCategories();
        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory(CategoryDTO categoryDTO) {
        CategoryDTO newCategoryDTO = categoryService.updateCategory(categoryDTO);
        return new ResponseEntity<>(newCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId) {
        String res = categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
