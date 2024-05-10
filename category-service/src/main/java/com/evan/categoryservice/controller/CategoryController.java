package com.evan.categoryservice.controller;

import com.evan.categoryservice.dto.CategoryDTO;
import com.evan.categoryservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Category Service",
        description = "CRUD REST API - Create Category, Get Category, Update Category, Delete Category"
)
@RestController
@RequestMapping("api/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @Operation(
            summary = "Create Category REST API",
            description = "Create category is used to save category into a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO saveCategoryDTO = categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(saveCategoryDTO, HttpStatus.CREATED);
    }

    @Operation(
            summary = "GET Category REST API",
            description = "Get category API is used to get a category from database by id"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long categoryId) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "GET Category REST API",
            description = "Get category is used to get category from database by name"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("name/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
        CategoryDTO categoriesDTO = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "GET Category API Service",
            description = "Get category is used to get all categories from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoriesDTO = categoryService.getAllCategories();
        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Category REST API",
            description = "Update category is used to update category from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO newCategoryDTO = categoryService.updateCategory(categoryDTO);
        return new ResponseEntity<>(newCategoryDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Category REST API",
            description = "Delete category is used to delete category from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @DeleteMapping("{categoryId}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long categoryId) {
        String res = categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
