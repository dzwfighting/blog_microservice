package com.evan.blogpost_service.repository;

import com.evan.blogpost_service.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jpa")
public interface PostRepository extends JpaRepository<Post, Long> {

}
