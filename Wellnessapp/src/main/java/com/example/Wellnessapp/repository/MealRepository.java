package com.example.Wellnessapp.repository;

import com.example.Wellnessapp.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal,Long> {
    List<Meal> findByMealType(String mealType);

    List<Meal> findByCalories(int calories);

    List<Meal> findByProtein(double protein);

    List<Meal> findByCarbs(double carbs);

    List<Meal> findByFats(double fats);

    List<Meal> findByUserId(Long userId);
}
