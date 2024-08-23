package com.example.Wellnessapp.repository;

import com.example.Wellnessapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

//    List<Task> findByCompleted(boolean completed);
}
