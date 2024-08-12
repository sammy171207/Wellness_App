package com.example.Wellnessapp.controller;

import com.example.Wellnessapp.config.JwtProvider;
import com.example.Wellnessapp.dto.UserDTO;
import com.example.Wellnessapp.dto.request.LoginRequest;
import com.example.Wellnessapp.dto.response.AuthResponse;
import com.example.Wellnessapp.entity.User;
import com.example.Wellnessapp.repository.UserRepository;
import com.example.Wellnessapp.service.CustomerUserDetailService;
import com.example.Wellnessapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailService customerUserDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserDTO userDTO) {
        // Create user
        User newUser = userService.createUser(userDTO);

        // Save user to the repository
        User savedUser = userRepository.save(newUser);

        // Create authentication token
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getUsername(),
                userDTO.getPassword() // Use the plain password for authentication
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwt = jwtProvider.generateToken(authentication);

        // Prepare the response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration successful");
        authResponse.setRole(savedUser.getRole().name());
        authResponse.setEmail(savedUser.getEmail());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user
        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        Authentication authentication =authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwt = jwtProvider.generateToken(authentication);

        // Retrieve the user from the database
        User user = userService.getUserByEmail(loginRequest.getEmail()).get();
       
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login successful");
        authResponse.setRole(user.getRole().name());
        authResponse.setEmail(user.getEmail());

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailService.loadUserByUsername(username);

        // Validate credentials
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }

        // Return authenticated token
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
