package com.example.Wellnessapp.dto;

import lombok.Data;

import java.util.Set;
@Data
public class UserDTO {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private Set<String> roles;
}
