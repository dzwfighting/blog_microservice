package com.evan.blogpost_service.mapper;

import com.evan.blogpost_service.dto.PostDTO;
import com.evan.blogpost_service.entity.Post;

public class PostMapper {
    public static PostDTO mapToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO(
                post.getPostId(),
                post.getAuthor(),
                post.getTitle(),
                post.getDescription(),
                post.getCategory(),
                post.getComments()
        );
        return postDTO;
    }

    public static Post mapToPost(PostDTO postDTO) {
        Post post = new Post(
                postDTO.getPostId(),
                postDTO.getAuthor(),
                postDTO.getTitle(),
                postDTO.getDescription(),
                postDTO.getCategory(),
                postDTO.getComments()
        );
        return post;
    }
}
