package com.evan.blogpost_service.repository;

import com.evan.blogpost_service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpa")
public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findByTitle(String title);
    public List<Post> findByAuthor(String author);
}
