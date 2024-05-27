package com.evan.user_service.service.impl;

import com.evan.user_service.dto.APIResponseDTO;
import com.evan.user_service.dto.UserDTO;
import com.evan.user_service.entity.User;
import com.evan.user_service.exception.ResourceDuplicateException;
import com.evan.user_service.exception.ResourceNotFoundException;
import com.evan.user_service.mapper.UserMapper;
import com.evan.user_service.repository.UserRepository;
import com.evan.user_service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;
//    private WebClient webClient;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        LOGGER.info("inside saveUser() - in serviceImpl, email is: " + userDTO.getEmail());
        User checkExist = userRepository.findByEmail(userDTO.getEmail());
        LOGGER.info("check if exist user: " + checkExist);
        if (checkExist != null) throw new ResourceDuplicateException("User", "email", userDTO.getEmail());
        User user = UserMapper.mapToUser(userDTO);
        User savedUser = userRepository.save(user);
        UserDTO savedUserDto = UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDTO getUserById(Long userid) {
        LOGGER.info("inside getUserById() - ");
        User user = userRepository.findById(userid).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userid)
        );

        UserDTO userDTO = UserMapper.mapToUserDto(user);

        return userDTO;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        LOGGER.info("inside getUserByEmail() - ");

        User user = userRepository.findByEmail(email);
//        System.out.println("find user by email: " + user);
       if (user == null) throw new ResourceNotFoundException("User", "email", email);
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

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultUser")
    @Override
    public UserDTO updateUserDTO(UserDTO userDTO) {
        LOGGER.info("inside updateUserDTO - ");
        User findUser = userRepository.findByEmail(userDTO.getEmail());
        if (findUser == null) throw new ResourceNotFoundException("User", "email", userDTO.getEmail());
        User updateUser = new User(
                userDTO.getEmail(),
                userDTO.getUsername(),
//                userDTO.getPassword(),
                userDTO.getRole(),
                userDTO.getPosts(),
                userDTO.getComments()
        );
        findUser.setEmail(updateUser.getEmail());
        findUser.setUsername(updateUser.getUsername());
//        findUser.setPassword(updateUser.getPassword());
        findUser.setPosts(updateUser.getPosts());
        findUser.setComments(updateUser.getComments());
        User savedUser = userRepository.save(findUser);
        UserDTO savedUserDTO = UserMapper.mapToUserDto(savedUser);
        return savedUserDTO;
    }


    @Override
    public String deleteUserByEmail(String email) {
        LOGGER.info("inside deleteUserByEmail() - ");
        User delUser = userRepository.findByEmail(email);
        if (delUser == null) throw new ResourceNotFoundException("User", "email", email);
        userRepository.delete(delUser);
        return "Success delete";
    }

    public UserDTO getDefaultUser(String email, Exception exception) {
        User user = userRepository.findByEmail(email);
        UserDTO userDTO = UserMapper.mapToUserDto(user);
        return userDTO;
    }
}
