package com.evan.commentservice.controller;

import com.evan.commentservice.dto.APIResponseDTO;
import com.evan.commentservice.dto.CommentDTO;
import com.evan.commentservice.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(
        name = "CRUD REST APIs for Comment Resource",
        description = "CRUD REST APIs - Create Comment, Update Comment, Get Comment, Delete Comment"
)
@RestController
@RequestMapping("api/comments")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @Operation(
            summary = "Create Comment REST API",
            description = "Create Comment REST API is used to save comment in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<APIResponseDTO> saveComment(@Valid @RequestBody CommentDTO commentDTO) {
        APIResponseDTO saveCommentDTO = commentService.saveComment(commentDTO);
        return new ResponseEntity<>(saveCommentDTO, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Comment REST API",
            description = "Get Comment REST API is used to get comment from database by id"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long commentId) {
        CommentDTO commentDTO = commentService.getCommentById(commentId);
        System.out.println("the commentDto is: " + commentDTO);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "GET Comment REST API",
            description = "GET Comment REST API is used to get all comments from database by reviewer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("reviewer/{reviewer}")
    public ResponseEntity<List<CommentDTO>> getCommentsByReviewer(@PathVariable String reviewer) {
        List<CommentDTO> commentsDTO = commentService.getCommentsByReviewer(reviewer);
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "GET Comment REST API",
            description = "Get comment by postId is used to get all comments from database by postId"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("post/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable Long postId) {
        List<CommentDTO> commentsDTO = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "GET Comment REST API",
            description = "Get comment is used to get all comments from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> commentsDTO = commentService.getAllComments();
        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Comment REST API",
            description = "Update comment is used to update comment to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PutMapping
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentDTO commentDTO) {
        CommentDTO newCommentDto = commentService.updateComment(commentDTO);
        return new ResponseEntity<>(newCommentDto, HttpStatus.OK);
    }

    @Operation(
            summary = "DELETE Comment REST API",
            description = "Delete comment is used to delete comment from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @DeleteMapping("{commentId}")
    public ResponseEntity<APIResponseDTO> deleteComment(@PathVariable Long commentId) {
        APIResponseDTO del = commentService.deleteCommentById(commentId);
        return new ResponseEntity<>(del, HttpStatus.OK);
    }
}