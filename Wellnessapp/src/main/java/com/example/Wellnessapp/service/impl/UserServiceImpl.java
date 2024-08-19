package com.example.Wellnessapp.service.impl;

import com.example.Wellnessapp.config.JwtProvider;
import com.example.Wellnessapp.dto.UserDTO;
import com.example.Wellnessapp.entity.Role;
import com.example.Wellnessapp.entity.User;
import com.example.Wellnessapp.exception.CustomException;
import com.example.Wellnessapp.repository.UserRepository;
import com.example.Wellnessapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;



    @Override
    public User createUser(UserDTO userDTO) {
        if (isUserExistByEmail(userDTO.getEmail())) {
            throw new CustomException("Email already exists.");
        }

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        return user;
    }

    @Override
    public User updateUser(Long userId, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new CustomException("User not found.");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);
        return  user;
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException("User not found.");
        }

        userRepository.deleteById(userId);

    }

    @Override
    public Optional<User> getUserById(Long userId) throws  Exception {
        return  userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserbyJwtToken(String token) {
        String email = jwtProvider.getEmailFromJwtToken(token);

        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
