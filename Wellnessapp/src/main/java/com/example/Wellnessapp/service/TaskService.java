package com.example.Wellnessapp.service;

import com.example.Wellnessapp.dto.request.TaskRequest;
import com.example.Wellnessapp.dto.request.TaskStatusUpdate;
import com.example.Wellnessapp.entity.Meal;
import com.example.Wellnessapp.entity.Task;
import com.example.Wellnessapp.entity.User;

import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest taskRequest, User user) throws Exception;
    Task updateTask(Long taskId, TaskRequest taskRequest) throws Exception;
    void deleteTask(Long taskId) throws Exception;
    Task getTaskById(Long taskId) throws Exception;
    List<Task> getAllTasks();
    List<Task> getTasksByUserId(Long userId) throws Exception;
    Task updateTaskStatus(Long taskId, TaskStatusUpdate taskStatusUpdateRequest) throws Exception;
}
