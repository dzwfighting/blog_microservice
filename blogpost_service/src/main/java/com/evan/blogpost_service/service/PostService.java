package com.evan.blogpost_service.service;

import com.evan.blogpost_service.dto.APIResponseDTO;
import com.evan.blogpost_service.dto.PostDTO;
import com.evan.blogpost_service.dto.UserDTO;
import com.evan.blogpost_service.entity.Post;

import java.util.List;

public interface PostService {
    public APIResponseDTO savePost(PostDTO post);
    public PostDTO getPostById(Long postId);
    public List<PostDTO> getPostsByTitle(String title);
    public List<PostDTO> getPostsByAuthor(String author);
    public List<PostDTO> getAllPosts();
    public PostDTO updatePost(PostDTO postDTO);
    public APIResponseDTO deletePost(Long postId);
}
