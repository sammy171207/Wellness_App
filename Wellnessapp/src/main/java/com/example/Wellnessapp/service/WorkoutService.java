package com.example.Wellnessapp.service;

import com.example.Wellnessapp.dto.request.WorkoutRequest;
import com.example.Wellnessapp.entity.User;
import com.example.Wellnessapp.entity.Workout;

import java.util.List;

public interface WorkoutService {

    Workout addWorkout(WorkoutRequest workoutRequest, User user) throws Exception;
    Workout getWorkoutById(Long id) throws Exception;
    List<Workout> getAllWorkouts();
    List<Workout> getWorkoutsByUserId(Long userId);
    Workout updateWorkout(Long id, WorkoutRequest workoutRequest) throws Exception;
    void deleteWorkout(Long id) throws Exception;
}
