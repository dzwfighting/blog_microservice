package com.evan.user_service.controller;

import com.evan.user_service.dto.UserDTO;
import com.evan.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, GetUser by email, Get User by Id, Get all users, delete User by email"
)

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDTO> createNewUser(@Valid @RequestBody UserDTO userDTO) {
        System.out.println("get save email is: " + userDTO.getEmail());
        UserDTO newUserDto = userService.saveUser(userDTO);
        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Users REST API",
            description = "Get Users REST API is used to get all users from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @Operation(
            summary = "Get User By Id REST API",
            description = "Get User By Id REST API is used for get user by userId from database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("id/{userid}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userid) {
        UserDTO userDTO = userService.getUserById(userid);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Get User By email REST API",
            description = "Get User By Email REST API is used for get user from database by email"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @GetMapping("email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Update User REST API",
            description = "Update User is used to update user from database by email"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO updateUserDTO = userService.updateUserDTO(userDTO);
        return new ResponseEntity<>(updateUserDTO, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User from database by email"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @DeleteMapping("{email}")
    public ResponseEntity<String> deleteUserByEmail(@PathVariable String email) {
//        System.out.println("delete email is: " + email);
        String delMsg = userService.deleteUserByEmail(email);
        return new ResponseEntity<>(delMsg, HttpStatus.OK);
    }




}
