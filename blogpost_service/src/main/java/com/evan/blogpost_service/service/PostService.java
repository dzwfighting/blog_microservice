package com.evan.blogpost_service.service;

import com.evan.blogpost_service.dto.PostDTO;

import java.util.List;

public interface PostService {
    public PostDTO savePost(PostDTO post);
    public PostDTO getPostById(Long postId);
    public List<PostDTO> getAllPosts();
    public PostDTO updatePost(PostDTO postDTO);
    public String deletePost(Long postId);
}
