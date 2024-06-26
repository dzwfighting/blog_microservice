package com.evan.commentservice.service;

import com.evan.commentservice.dto.APIResponseDTO;
import com.evan.commentservice.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    public APIResponseDTO saveComment(CommentDTO commentDTO);
    public CommentDTO getCommentById(Long commentId);
    public List<CommentDTO> getCommentsByReviewer(String reviewer);
    public List<CommentDTO> getCommentsByPostId(Long postId);
    public List<CommentDTO> getAllComments();
    public CommentDTO updateComment(CommentDTO commentDTO);
    public APIResponseDTO deleteCommentById(Long commentId);
}
