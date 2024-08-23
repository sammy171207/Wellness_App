package com.example.Wellnessapp.service.impl;

import com.example.Wellnessapp.dto.request.WorkoutRequest;
import com.example.Wellnessapp.entity.Meal;
import com.example.Wellnessapp.entity.User;
import com.example.Wellnessapp.entity.Workout;
import com.example.Wellnessapp.exception.CustomException;
import com.example.Wellnessapp.repository.UserRepository;
import com.example.Wellnessapp.repository.WorkoutRepository;
import com.example.Wellnessapp.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkoutServiceImpl implements WorkoutService {
     @Autowired
     private WorkoutRepository workoutRepository;

     @Autowired
     private UserRepository userRepository;
    @Override
    public Workout addWorkout(WorkoutRequest workoutRequest, User user) throws Exception {
        Workout workout=new Workout();
         workout.setWorkoutType(workoutRequest.getWorkoutType());
         workout.setDuration(workoutRequest.getDuration());
         workout.setDescription(workoutRequest.getDescription());
         workout.setWorkoutTime(workoutRequest.getWorkoutTime());
         workout.setIntensity(workoutRequest.getIntensity());
         workout.setCaloriesBurned(workoutRequest.getCaloriesBurned());
         workout.setUser(user);

         return workoutRepository.save(workout);
    }

    @Override
    public Workout getWorkoutById(Long id) throws Exception {
        return workoutRepository.findById(id).orElseThrow(() -> new CustomException("Meal not found with id " + id));

    }

    @Override
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    @Override
    public List<Workout> getWorkoutsByUserId(Long userId) {
     return  workoutRepository.findByUserId(userId);
    }

    @Override
    public Workout updateWorkout(Long id, WorkoutRequest workoutRequest) throws Exception {
        Workout existingWorkout = workoutRepository.findById(id)
                .orElseThrow(() -> new CustomException("Workout not found with id " + id));

        existingWorkout.setWorkoutType(workoutRequest.getWorkoutType());
        existingWorkout.setDescription(workoutRequest.getDescription());
        existingWorkout.setIntensity(workoutRequest.getIntensity());
        existingWorkout.setDuration(workoutRequest.getDuration());
        existingWorkout.setCaloriesBurned(workoutRequest.getCaloriesBurned());

        return workoutRepository.save(existingWorkout);
    }

    @Override
    public void deleteWorkout(Long id) throws Exception {
        workoutRepository.deleteById(id);
    }
}
