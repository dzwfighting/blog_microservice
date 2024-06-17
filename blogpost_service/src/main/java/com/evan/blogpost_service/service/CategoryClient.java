package com.evan.blogpost_service.service;

import com.evan.blogpost_service.dto.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(url = "http://localhost:8084", value = "category-service")
@FeignClient(name = "category-service")
public interface CategoryClient {
    @GetMapping("api/categories/name/{name}")
    CategoryDTO getCategoryByName(@PathVariable String name);

    @PutMapping("api/categories")
    CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO);
}
