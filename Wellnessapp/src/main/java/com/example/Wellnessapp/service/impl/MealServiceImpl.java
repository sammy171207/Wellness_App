package com.example.Wellnessapp.service.impl;

import com.example.Wellnessapp.config.JwtProvider;
import com.example.Wellnessapp.dto.request.MealRequest;
import com.example.Wellnessapp.entity.Meal;
import com.example.Wellnessapp.entity.User;
import com.example.Wellnessapp.exception.CustomException;
import com.example.Wellnessapp.repository.MealRepository;
import com.example.Wellnessapp.repository.UserRepository;
import com.example.Wellnessapp.service.MealService;
import com.example.Wellnessapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;
    @Override
    public Meal addMeal(MealRequest mealRequest, User user) throws Exception {
        Meal meal = new Meal();
        meal.setMealType(mealRequest.getMealType());
        meal.setDescription(mealRequest.getDescription());
        meal.setIngredients(mealRequest.getIngredients());
        meal.setCalories(mealRequest.getCalories());
        meal.setProtein(mealRequest.getProtein());
        meal.setCarbs(mealRequest.getCarbs());
        meal.setFats(mealRequest.getFats());
        meal.setMealTime(mealRequest.getMealTime());
        meal.setUser(user);

        return mealRepository.save(meal);
    }

    @Override
    public Meal getByUserId(Long userId) throws Exception {
        return (Meal) userRepository.findById(userId)
                .map(User::getMeals)
                .orElseThrow(() -> new CustomException("User not found with id " + userId));
    }

    @Override
    public Meal updateMeal(Long id, MealRequest mealRequest) throws Exception {
        Meal existingMeal = mealRepository.findById(id)
                .orElseThrow(() -> new CustomException("Meal not found with id " + id));

        existingMeal.setMealType(mealRequest.getMealType());
        existingMeal.setDescription(mealRequest.getDescription());
        existingMeal.setIngredients(mealRequest.getIngredients());
        existingMeal.setCalories(mealRequest.getCalories());
        existingMeal.setProtein(mealRequest.getProtein());
        existingMeal.setCarbs(mealRequest.getCarbs());
        existingMeal.setFats(mealRequest.getFats());
        existingMeal.setMealTime(mealRequest.getMealTime());

        return mealRepository.save(existingMeal);
    }

    @Override
    public void deleteMeal(Long id) throws Exception {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new CustomException("Meal not found with id " + id));
        mealRepository.delete(meal);
    }

    @Override
    public Meal getMealById(Long id) throws Exception {
        return mealRepository.findById(id)
                .orElseThrow(() -> new CustomException("Meal not found with id " + id));
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @Override
    public List<Meal> findMealsByCalories(int calories) {
        return mealRepository.findByCalories(calories);
    }

    @Override
    public List<Meal> findMealsByProtein(double protein) {
        return mealRepository.findByProtein(protein);
    }

    @Override
    public List<Meal> findMealsByCarbs(double carbs) {
        return mealRepository.findByCarbs(carbs);
    }

    @Override
    public List<Meal> findMealsByFats(double fats) {
        return mealRepository.findByFats(fats);
    }

    @Override
    public List<Meal> findMealsByUserId(Long userId) {
        return mealRepository.findByUserId(userId);
    }
}
