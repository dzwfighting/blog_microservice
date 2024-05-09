package com.evan.categoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String name;
    private String description;
    private List<Long> postIds;

    public CategoryDTO(String name, String description, List<Long> postIds) {
        this.name = name;
        this.description = description;
        this.postIds = postIds;
    }

    public CategoryDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
