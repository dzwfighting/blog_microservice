package com.evan.commentservice.mapper;

import com.evan.commentservice.dto.CommentDTO;
import com.evan.commentservice.entity.Comment;

public class CommentMapper {
    public static CommentDTO mapToCommentDto(Comment comment) {
        CommentDTO commentDTO = new CommentDTO(
                comment.getCommentId(),
                comment.getReviewer(),
                comment.getTitle(),
                comment.getBody(),
                comment.getPostId()
        );
        return commentDTO;
    }

    public static Comment mapToComment(CommentDTO commentDTO) {
        Comment comment = new Comment(
                commentDTO.getCommentId(),
                commentDTO.getReviewer(),
                commentDTO.getTitle(),
                commentDTO.getBody(),
                commentDTO.getPostId()
        );
        return comment;
    }
}
