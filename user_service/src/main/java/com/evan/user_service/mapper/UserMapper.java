package com.evan.user_service.mapper;

import com.evan.user_service.dto.UserDTO;
import com.evan.user_service.entity.User;

public class UserMapper {
    public static UserDTO mapToUserDto(User user) {
        UserDTO userDTO = new UserDTO(
                user.getUserId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getPosts(),
                user.getComments()
        );
        return userDTO;
    }

    public static User mapToUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getUserId(),
                userDTO.getEmail(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getRole(),
                userDTO.getPosts(),
                userDTO.getComments()
        );
        return user;
    }
}
