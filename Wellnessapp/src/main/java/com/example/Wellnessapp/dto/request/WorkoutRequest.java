package com.example.Wellnessapp.dto.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class WorkoutRequest {
    private String title;
    private String description;
    private int duration; // duration in minutes
    private String intensity; // e.g., Low, Medium, High
    private LocalDate date;
}
