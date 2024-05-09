package com.evan.blogpost_service.controller;

import com.evan.blogpost_service.dto.PostDTO;
import com.evan.blogpost_service.exception.ResourceNotFoundException;
import com.evan.blogpost_service.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.OnError;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Post Service",
        description = "CRUD REST APIs - Create Post, Update Post, Get Post, Delete Post"
)
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @Operation(
            summary = "Create REST API",
            description = "Create Post REST API is used to save post in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<PostDTO> savePost(@Valid @RequestBody PostDTO postDTO) {
        PostDTO savePostDto = postService.savePost(postDTO);
        return new ResponseEntity<>(savePostDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get REST API",
            description = "Get Post by Id is used to get postId from database and find correspond post"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Get REST API",
            description = "Get Posts by title is used to get all posts which is this title"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("title/{title}")
    public ResponseEntity<List<PostDTO>> getPostsByTitle(@PathVariable String title) {
        List<PostDTO> postsDTO = postService.getPostsByTitle(title);
        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "GET REST API",
            description = "Get posts by author is used to get all post which is this author"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("author/{author}")
    public ResponseEntity<List<PostDTO>> getPostsByAuthor(@PathVariable String author) {
        List<PostDTO> postsDto = postService.getPostsByAuthor(author);
        return new ResponseEntity<>(postsDto, HttpStatus.OK);
    }

    @Operation(
            summary = "GET REST API",
            description = "Get all posts is used to get all posts from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @Operation(
            summary = "UPDATE REST API",
            description = "Update post is used to update a post from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PutMapping
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO) {
        PostDTO checkPost = postService.getPostById(postDTO.getPostId());
        if (checkPost == null) throw new ResourceNotFoundException("Post", "postId", postDTO.getPostId());
        PostDTO updatePost = postService.updatePost(postDTO);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    @Operation(
            summary = "DELETE REST API",
            description = "Delete post by id is used to delete the post from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @DeleteMapping("{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable Long postId) {
        PostDTO checkPostDto = postService.getPostById(postId);
        if (checkPostDto == null) throw new ResourceNotFoundException("Post", "postId", postId);
        String delRes = postService.deletePost(postId);
        return new ResponseEntity<>(delRes, HttpStatus.OK);
    }
}
