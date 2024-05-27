package com.evan.commentservice.service.impl;

import com.evan.commentservice.dto.APIResponseDTO;
import com.evan.commentservice.dto.CommentDTO;
import com.evan.commentservice.dto.PostDTO;
import com.evan.commentservice.dto.UserDTO;
import com.evan.commentservice.entity.Comment;
import com.evan.commentservice.exception.ResourceNotFoundException;
import com.evan.commentservice.mapper.CommentMapper;
import com.evan.commentservice.repository.CommentRepository;
import com.evan.commentservice.service.CommentService;
import com.evan.commentservice.service.PostClient;
import com.evan.commentservice.service.UserClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    public static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);
    private CommentRepository commentRepository;
    private UserClient userClient;
    private PostClient postClient;

    @Override
    public APIResponseDTO saveComment(CommentDTO commentDTO) {
        LOGGER.info("in saveComment()");
//        Todo: get current user information
        String email = "user1@gmail.com";
        commentDTO.setReviewer(email);
        Comment convertToComment = CommentMapper.mapToComment(commentDTO);
        Comment newComment = commentRepository.save(convertToComment);
        CommentDTO newCommentDto = CommentMapper.mapToCommentDto(newComment);

//        check data if is exist
        UserDTO userDTO = userClient.getUser(email);
        Long postId = newCommentDto.getPostId();
        LOGGER.info("when save comment, the postId is: " + postId);
        PostDTO postDTO = postClient.getPostById(postId);
        if (userDTO == null) throw new ResourceNotFoundException("user", "email", email);
        if (postDTO == null) throw new ResourceNotFoundException("post", "postId", postId);

//       add commentId into user
        Set<Long> comments = userDTO.getComments();
        comments.add(newCommentDto.getCommentId());
        userDTO.setComments(comments);
        UserDTO newUserDTO = userClient.updateUser(userDTO);

//        Add commentId to post
        Set<Long> commentIds = postDTO.getComments();
        commentIds.add(newCommentDto.getCommentId());
        postDTO.setComments(commentIds);
        PostDTO newPostDTO = postClient.updatePost(postDTO);

//        Create apiResponse;
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setCommentDTO(newCommentDto);
        apiResponseDTO.setUserDTO(newUserDTO);
        apiResponseDTO.setPostDTO(newPostDTO);
        return apiResponseDTO;
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
    public APIResponseDTO deleteCommentById(Long commentId) {
        Comment delComment = commentRepository.findById(commentId).get();
        if (delComment == null) throw new ResourceNotFoundException("Comment", "id", commentId);
        String email = delComment.getReviewer();
        Long postId = delComment.getPostId();
        UserDTO userDTO = userClient.getUser(email);
        PostDTO postDTO = postClient.getPostById(postId);
        if (userDTO == null) throw new ResourceNotFoundException("user", "email", email);
        if (postDTO == null) throw new ResourceNotFoundException("post", "postId", postId);
        commentRepository.delete(delComment);

//        delete commentId from user
        Set<Long> commentsFromUser = userDTO.getComments();
        if (commentsFromUser.contains(commentId)) commentsFromUser.remove(commentId);
        userDTO.setComments(commentsFromUser);
        UserDTO newUserDTO = userClient.updateUser(userDTO);

//        delete commentId from post
        Set<Long> commentsFromPost = postDTO.getComments();
        if (commentsFromPost.contains(commentId)) commentsFromPost.remove(commentId);
        postDTO.setComments(commentsFromPost);
        PostDTO newPostDTO = postClient.updatePost(postDTO);

//        create apiResponse
        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setUserDTO(newUserDTO);
        apiResponseDTO.setPostDTO(newPostDTO);

        return apiResponseDTO;
    }
}
