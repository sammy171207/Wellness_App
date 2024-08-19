package com.example.Wellnessapp.controller;

import com.example.Wellnessapp.dto.request.MealRequest;
import com.example.Wellnessapp.dto.response.ApiResponse;
import com.example.Wellnessapp.entity.Meal;
import com.example.Wellnessapp.entity.User;
import com.example.Wellnessapp.exception.CustomException;
import com.example.Wellnessapp.service.MealService;
import com.example.Wellnessapp.service.UserService;
import com.example.Wellnessapp.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;
    //need to check the postmapping
    @PostMapping
    public ResponseEntity<Meal> addMeal(@RequestBody MealRequest mealRequest,
                                               @RequestHeader("Authorization") String jwt) throws Exception {


    User user=userService.findUserbyJwtToken(jwt);
    Meal meal=mealService.addMeal(mealRequest,user);
    return new ResponseEntity<>(meal,HttpStatus.OK);



    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMealById(@PathVariable Long id) {
        try {
            Meal meal = mealService.getMealById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Meal retrieved successfully", meal));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Meal not found", null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
   //check
    @GetMapping("/user")
    public ResponseEntity<List<Meal>> getMealsByUser(@RequestHeader("Authorization") String jwt) {
         User user=userService.findUserbyJwtToken(jwt);
         Long user_id=user.getId();
        List<Meal> meals = mealService.findMealsByUserId(user_id);
        return ResponseEntity.ok(meals);
    }

   //checked
    @GetMapping
    public ResponseEntity<List<Meal>> getAllMeals() {
        List<Meal> meals = mealService.getAllMeals();
        return ResponseEntity.ok(meals);
    }


    /// checked
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) {
        try {
            // Extract the user from the JWT token
            User user = userService.findUserbyJwtToken(jwt);
            Long userId = user.getId();
            System.out.println("Extracted User ID: " + userId);
            // Get the existing meal by ID
            Meal existingMeal = mealService.getMealById(id);

            // Check if the authenticated user is the owner of the meal
            if (!existingMeal.getUser().getId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Forbidden if user does not own the meal
            }

            // Proceed to delete the meal
            mealService.deleteMeal(id);
            return ResponseEntity.noContent().build(); // Return no content status on successful deletion
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return not found status if the meal or user is not found
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Long id,
                                           @RequestBody MealRequest mealRequest,
                                           @RequestHeader("Authorization") String jwt) {
        try {
            User user=userService.findUserbyJwtToken(jwt);
            Long userId = user.getId();
            System.out.println("Extracted User ID: " + userId);
            // Get the existing meal by ID
            Meal existingMeal = mealService.getMealById(id);
            System.out.println("Existing Meal: " + existingMeal);
            // Check if the authenticated user is the owner of the meal
            if (!existingMeal.getUser().getId().equals(userId)) {
                System.out.println("User ID mismatch. Cannot update meal.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Forbidden if user does not own the meal
            }
            // Update the meal
            Meal updatedMeal = mealService.updateMeal(id, mealRequest);
            System.out.println("Updated Meal: " + updatedMeal);
            return ResponseEntity.ok(updatedMeal);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return not found status if the meal or user is not found
        }
    }

}
