package com.evan.categoryservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Schema(
        name = "CategoryDTO Model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @Schema(
            description = "Category Id"
    )
    private Long categoryId;

    @Schema(
            description = "Category Name"
    )
    @NotEmpty(message = "name should not be empty")
    private String name;

    @Schema(
            description = "Category Description"
    )
    @NotEmpty(message = "description should not be empty")
    private String description;

    @Schema(
            description = "Category PostIds"
    )
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

    public CategoryDTO(Long categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }
}
