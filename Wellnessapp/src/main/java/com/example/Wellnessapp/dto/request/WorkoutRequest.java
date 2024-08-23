package com.example.Wellnessapp.dto.request;

import com.example.Wellnessapp.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class WorkoutRequest {
    private String title;
    private String workoutType;
    private String description;
    private int duration;
    private String intensity;
    private int caloriesBurned;
    private LocalDateTime workoutTime;
    private User user;// e.g., Low, Medium, High
}
