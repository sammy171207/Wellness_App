package com.example.Wellnessapp.service.impl;

import com.example.Wellnessapp.dto.request.TaskRequest;
import com.example.Wellnessapp.dto.request.TaskStatusUpdate;
import com.example.Wellnessapp.entity.Task;
import com.example.Wellnessapp.entity.User;
import com.example.Wellnessapp.repository.TaskRepository;
import com.example.Wellnessapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Task createTask(TaskRequest taskRequest, User user) throws Exception {
        Task task=new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setPriority(taskRequest.getPriority());
        task.setDueDate(taskRequest.getDueDate());
        task.setUser(user);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, TaskRequest taskRequest) throws Exception {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found with id " + taskId));
        // Update the task details
        existingTask.setTitle(taskRequest.getTitle());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setStatus(taskRequest.getStatus());
        existingTask.setPriority(taskRequest.getPriority());
        existingTask.setDueDate(taskRequest.getDueDate());
        // Save the updated task
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long taskId) throws Exception {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found"));
        taskRepository.delete(task);
    }

    @Override
    public Task getTaskById(Long taskId) throws Exception {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) throws Exception {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public Task updateTaskStatus(Long taskId, TaskStatusUpdate taskStatusUpdateRequest) throws Exception {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found with id " + taskId));
        // Update only the status
        if (taskStatusUpdateRequest.getStatus() != null) {
            existingTask.setStatus(taskStatusUpdateRequest.getStatus());
        }
        // Save the updated task
        return taskRepository.save(existingTask);
    }
}
