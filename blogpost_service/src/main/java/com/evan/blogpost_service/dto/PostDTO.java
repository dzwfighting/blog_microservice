package com.evan.blogpost_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long postId;

//    @NotEmpty(message = "author email should not be empty")
    private String author;

    @NotEmpty(message = "title should not be empty")
    private String title;

    @NotEmpty(message = "description should not be empty")
    private String description;

    @NotEmpty(message = "category should not be empty")
    private String category;

    private Set<Long> comments;

    public PostDTO(String author, String title, String description, String category, Set<Long> comments) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.category = category;
        this.comments = comments;
    }

    public PostDTO(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }
}
