package com.evan.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

//    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int role;

    @ElementCollection
    private Set<Long> posts;

    @ElementCollection
    private Set<Long> comments;

    public User(String email, String username, String password, int role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String email, String username, String password, int role, Set<Long> posts, Set<Long> comments) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.posts = posts;
        this.comments = comments;
    }

    public User(String email, String username, int role, Set<Long> posts, Set<Long> comments) {
        this.email = email;
        this.username = username;
        this.role = role;
        this.posts = posts;
        this.comments = comments;
    }
}
