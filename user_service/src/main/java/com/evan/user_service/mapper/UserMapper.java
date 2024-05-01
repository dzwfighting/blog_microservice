package com.evan.user_service.mapper;

import com.evan.user_service.dto.UserDTO;
import com.evan.user_service.entity.User;

public class UserMapper {
    public static UserDTO mapToUserDto(User user) {
        UserDTO userDTO = new UserDTO(
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
        return userDTO;
    }

    public static User mapToUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getEmail(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getRole()
        );
        return user;
    }
}
