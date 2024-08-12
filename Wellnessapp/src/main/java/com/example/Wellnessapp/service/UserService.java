package com.example.Wellnessapp.service;

import com.example.Wellnessapp.dto.UserDTO;
import com.example.Wellnessapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDTO userDTO);
    User updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);
    Optional<User> getUserById(Long userId);
    List<User> getAllUsers();
    Optional<User> getUserByEmail(String email);
    User findUserByJwtToken(String token);
    boolean isUserExistByEmail(String email);
}
