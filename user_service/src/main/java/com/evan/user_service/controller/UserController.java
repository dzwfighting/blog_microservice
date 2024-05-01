package com.evan.user_service.controller;

import com.evan.user_service.dto.UserDTO;
import com.evan.user_service.entity.User;
import com.evan.user_service.mapper.UserMapper;
import com.evan.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createNewUser(@RequestBody UserDTO userDTO) {
        System.out.println("get save email is: " + userDTO.getEmail());
        UserDTO newUserDto = userService.saveUser(userDTO);
        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("{userid}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userid) {
        UserDTO userDTO = userService.getUserById(userid);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("get/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updateUserDTO = userService.updateUserDTO(userDTO);
        return new ResponseEntity<>(updateUserDTO, HttpStatus.OK);
    }

    @DeleteMapping("{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
//        System.out.println("delete email is: " + email);
        String delMsg = userService.deleteUserByEmail(email);
        return new ResponseEntity<>(delMsg, HttpStatus.OK);
    }




}
