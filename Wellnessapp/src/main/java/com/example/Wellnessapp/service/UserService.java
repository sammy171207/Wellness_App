package com.example.Wellnessapp.service;

import com.example.Wellnessapp.dto.UserDTO;
import com.example.Wellnessapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDTO userDTO);
    User updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);
    Optional<User> getUserById(Long userId) throws  Exception;
    List<User> getAllUsers();
    User getUserByEmail(String email);

    boolean isUserExistByEmail(String email);

    User findUserbyJwtToken(String jwt);
}
