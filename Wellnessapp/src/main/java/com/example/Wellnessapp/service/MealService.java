package com.example.Wellnessapp.service;

import com.example.Wellnessapp.dto.request.MealRequest;
import com.example.Wellnessapp.entity.Meal;
import com.example.Wellnessapp.entity.User;

import java.util.List;

public interface MealService {
    Meal addMeal(MealRequest mealRequest, User user) throws Exception;
    Meal getByUserId(Long UserId) throws Exception;
    Meal updateMeal(Long id, MealRequest mealRequest) throws Exception;

    void deleteMeal(Long id) throws Exception;

    Meal getMealById(Long id) throws Exception;

    List<Meal> getAllMeals();

    List<Meal> findMealsByCalories(int calories);

    List<Meal> findMealsByProtein(double protein);

    List<Meal> findMealsByCarbs(double carbs);

    List<Meal> findMealsByFats(double fats);

    List<Meal> findMealsByUserId(Long userId);
}
