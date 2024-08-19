package com.example.Wellnessapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MealRequest {
    private String mealType;
    private String description;
    private List<String> ingredients;
    private int calories;
    private double protein;
    private double carbs;
    private double fats;
    private LocalDateTime mealTime;

    private Long userId;
}
