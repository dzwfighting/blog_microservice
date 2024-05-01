package com.evan.user_service.service.impl;

import com.evan.user_service.dto.UserDTO;
import com.evan.user_service.entity.User;
import com.evan.user_service.mapper.UserMapper;
import com.evan.user_service.repository.UserRepository;
import com.evan.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        LOGGER.info("inside saveUser() - in serviceImpl, email is: " + userDTO.getEmail());
        User user = UserMapper.mapToUser(userDTO);
        User savedUser = userRepository.save(user);
        UserDTO savedUserDto = UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDTO getUserById(Long userid) {
        LOGGER.info("inside getUserById() - ");
        User user = userRepository.findById(userid).get();
        UserDTO userDTO = UserMapper.mapToUserDto(user);

        return userDTO;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        LOGGER.info("inside getUserByEmail() - ");
        User user = userRepository.findByEmail(email);
        UserDTO userDTO = UserMapper.mapToUserDto(user);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        LOGGER.info("inside getAllUsers() - ");
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        return usersDTO;
    }

    @Override
    public UserDTO updateUserDTO(UserDTO userDTO) {
        LOGGER.info("inside updateUserDTO - ");
        User findUser = userRepository.findByEmail(userDTO.getEmail());
        User updateUser = new User(
                userDTO.getEmail(),
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getRole()
        );
        findUser.setEmail(updateUser.getEmail());
        findUser.setUsername(updateUser.getUsername());
        User savedUser = userRepository.save(findUser);
        UserDTO savedUserDTO = UserMapper.mapToUserDto(savedUser);
        return savedUserDTO;
    }

    @Override
    public String deleteUserByEmail(String email) {
        LOGGER.info("inside deleteUserByEmail() - ");
        User delUser = userRepository.findByEmail(email);
        userRepository.delete(delUser);
        return "Success delete";
    }
}
