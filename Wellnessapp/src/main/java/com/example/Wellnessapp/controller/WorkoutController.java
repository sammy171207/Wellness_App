package com.example.Wellnessapp.controller;

import com.example.Wellnessapp.config.JwtProvider;
import com.example.Wellnessapp.dto.request.WorkoutRequest;
import com.example.Wellnessapp.entity.User;
import com.example.Wellnessapp.entity.Workout;
import com.example.Wellnessapp.service.UserService;
import com.example.Wellnessapp.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping
    public ResponseEntity<Workout>addWorkout(@RequestBody WorkoutRequest request ,  @RequestHeader("Authorization") String jwt)throws Exception{
        User user=userService.findUserbyJwtToken(jwt);
        Workout workout=workoutService.addWorkout(request,user);
        return  new ResponseEntity<>(workout, HttpStatus.CREATED);}


}
