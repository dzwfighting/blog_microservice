package com.evan.commentservice.service;

import com.evan.commentservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(url = "http://localhost:8081", value = "user-service")
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("api/users/email/{email}")
    UserDTO getUser(@PathVariable("email") String author);

    @PutMapping("api/users")
    UserDTO updateUser(@RequestBody UserDTO userDTO);

}
