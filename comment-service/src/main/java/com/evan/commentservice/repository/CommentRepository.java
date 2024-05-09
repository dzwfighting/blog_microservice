package com.evan.commentservice.repository;

import com.evan.commentservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpa")
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findCommentsByReviewer(String reviewer);
    public List<Comment> findCommentsByPostId(Long postId);
}
