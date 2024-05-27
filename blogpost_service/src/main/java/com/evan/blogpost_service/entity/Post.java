package com.evan.blogpost_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String category;

    @ElementCollection
    Set<Long> comments;

    public Post(Long postId, String author, String title, String description, String category) {
        this.postId = postId;
        this.author = author;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public Post(String author, String title, String description, String category) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public Post(String author, String title, String description, String category, Set<Long> comments) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.category = category;
        this.comments = comments;
    }
}
