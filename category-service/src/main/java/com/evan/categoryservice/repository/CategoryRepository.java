package com.evan.categoryservice.repository;

import com.evan.categoryservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findCategoryByName(String name);
}
