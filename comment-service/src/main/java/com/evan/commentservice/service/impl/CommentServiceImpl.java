package com.evan.commentservice.service.impl;

import com.evan.commentservice.dto.CommentDTO;
import com.evan.commentservice.entity.Comment;
import com.evan.commentservice.exception.ResourceNotFoundException;
import com.evan.commentservice.mapper.CommentMapper;
import com.evan.commentservice.repository.CommentRepository;
import com.evan.commentservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    public static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);
    private CommentRepository commentRepository;

    @Override
    public CommentDTO saveComment(CommentDTO commentDTO) {
        LOGGER.info("in saveComment()");
        Comment convertToComment = CommentMapper.mapToComment(commentDTO);
        Comment newComment = commentRepository.save(convertToComment);
        CommentDTO newCommentDto = CommentMapper.mapToCommentDto(newComment);
        return newCommentDto;
    }

    @Override
    public CommentDTO getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        if (comment == null) throw new ResourceNotFoundException("Comment", "id", commentId);
        CommentDTO commentDTO = CommentMapper.mapToCommentDto(comment);
        return commentDTO;
    }

    @Override
    public List<CommentDTO> getCommentsByReviewer(String reviewer) {
        List<Comment> comments = commentRepository.findCommentsByReviewer(reviewer);
        if (comments == null) throw new ResourceNotFoundException("Comment", "reviewer", reviewer);
        List<CommentDTO> commentsDTO = comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
        return commentsDTO;
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);
        if (comments == null) throw new ResourceNotFoundException("Comment", "postId", postId);
        List<CommentDTO> commentsDTO = comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
        return commentsDTO;
    }

    @Override
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDTO> commentsDTO = comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
        return commentsDTO;
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO) {
        Comment oldComment = commentRepository.findById(commentDTO.getCommentId()).get();
        if (oldComment == null) throw new ResourceNotFoundException("Comment", "id", commentDTO.getCommentId());
        Comment newComment = CommentMapper.mapToComment(commentDTO);
        oldComment.setCommentId(newComment.getCommentId());
        oldComment.setTitle(newComment.getTitle());
        oldComment.setBody(newComment.getBody());
        oldComment.setReviewer(newComment.getReviewer());
        oldComment.setPostId(newComment.getPostId());
        Comment saveComment = commentRepository.save(oldComment);
        CommentDTO saveCommentDto = CommentMapper.mapToCommentDto(saveComment);
        return saveCommentDto;
    }

    @Override
    public String deleteCommentById(Long commentId) {
        Comment delComment = commentRepository.findById(commentId).get();
        if (delComment == null) throw new ResourceNotFoundException("Comment", "id", commentId);
        commentRepository.delete(delComment);
        return "delete success";
    }
}
