package com.evan.user_service.service;

import com.evan.user_service.dto.APIResponseDTO;
import com.evan.user_service.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);

    UserDTO getUserById(Long userid);
    UserDTO getUserByEmail(String email);
    List<UserDTO> getAllUsers();

    UserDTO updateUserDTO(UserDTO userDTO);
//    APIResponseDTO addPostToUserDTO(Long postId);

    String deleteUserByEmail(String email);


}
