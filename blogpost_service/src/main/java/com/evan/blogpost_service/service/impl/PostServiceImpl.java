package com.evan.blogpost_service.service.impl;

import com.evan.blogpost_service.dto.PostDTO;
import com.evan.blogpost_service.entity.Post;
import com.evan.blogpost_service.exception.ResourceDuplicateException;
import com.evan.blogpost_service.exception.ResourceNotFoundException;
import com.evan.blogpost_service.mapper.PostMapper;
import com.evan.blogpost_service.repository.PostRepository;
import com.evan.blogpost_service.service.PostService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);
    private PostRepository postRepository;

    @Override
    public PostDTO savePost(PostDTO post) {
        LOGGER.info("in savePost()");
        Post checkPost = postRepository.findById(post.getPostId()).get();
        if (checkPost != null) throw new ResourceDuplicateException("Post", "id", post.getPostId());
        Post convertPost = PostMapper.mapToPost(post);
        Post newPost = postRepository.save(convertPost);
        PostDTO postDto = PostMapper.mapToPostDTO(newPost);
        return postDto;
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        if (postId == null) throw new ResourceNotFoundException("Post", "id", postId);
        PostDTO postDto = PostMapper.mapToPostDTO(post);
        return postDto;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOS = posts.stream().map(PostMapper::mapToPostDTO).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) {
        Post oldPost = postRepository.findById(postDTO.getPostId()).get();
        if (oldPost == null) throw new ResourceNotFoundException("Post", "id", postDTO.getPostId());
        oldPost.setAuthor(postDTO.getAuthor());
        oldPost.setTitle(postDTO.getTitle());
        oldPost.setDescription(postDTO.getDescription());
        oldPost.setCategory(postDTO.getCategory());
        oldPost.setComments(postDTO.getComments());
        Post updatePost = postRepository.save(oldPost);
        PostDTO updatePostDto = PostMapper.mapToPostDTO(updatePost);
        return updatePostDto;
    }

    @Override
    public String deletePost(Long postId) {
        Post delPost = postRepository.findById(postId).get();
        if (delPost == null) throw new ResourceNotFoundException("Post", "id", postId);
        postRepository.delete(delPost);
        return "Success delete";
    }
}
