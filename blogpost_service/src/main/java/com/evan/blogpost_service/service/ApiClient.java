package com.evan.blogpost_service.service;

import com.evan.blogpost_service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081", value = "USER-SERVICE")
public interface ApiClient {
    @GetMapping("api/users/get/{email}")
    UserDTO getUser(@PathVariable("email") String author);
}
