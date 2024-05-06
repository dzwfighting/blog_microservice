package com.evan.blogpost_service.controller;

import com.evan.blogpost_service.dto.PostDTO;
import com.evan.blogpost_service.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO) {
        PostDTO checkPostDto = postService.getPostById(postDTO.getPostId());
        PostDTO savePostDto = postService.savePost(postDTO);
        return new ResponseEntity<>(savePostDto, HttpStatus.CREATED);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) {
        PostDTO updatePost = postService.updatePost(postDTO);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }
}
