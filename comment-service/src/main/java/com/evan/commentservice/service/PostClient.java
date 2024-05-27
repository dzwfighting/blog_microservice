package com.evan.commentservice.service;

import com.evan.commentservice.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8082", value = "blogpost-service")
public interface PostClient {
    @GetMapping("api/posts/{postId}")
    PostDTO getPostById(@PathVariable Long postId);

    @PutMapping("api/posts")
    PostDTO updatePost(@RequestBody PostDTO postDTO);
}
