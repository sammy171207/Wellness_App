package com.example.Wellnessapp.repository;

import com.example.Wellnessapp.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout,Long>{
    List<Workout> findByUserId(Long userId);
}
