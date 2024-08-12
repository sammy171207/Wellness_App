package com.example.Wellnessapp.controller;

import com.example.Wellnessapp.entity.User;
import com.example.Wellnessapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/profile")
    public ResponseEntity<User> findUserbyJwtToken(@RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserbyJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
