package com.evan.commentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long postId;
    private String author;
    private String title;
    private String description;
    private String category;
    private Set<Long> comments;

    public PostDTO(String author, String title, String description, String category, Set<Long> comments) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.category = category;
        this.comments = comments;
    }
}
