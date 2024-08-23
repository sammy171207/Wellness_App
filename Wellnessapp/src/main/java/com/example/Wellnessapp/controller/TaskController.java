package com.example.Wellnessapp.controller;

import com.example.Wellnessapp.dto.request.TaskRequest;
import com.example.Wellnessapp.dto.request.TaskStatusUpdate;
import com.example.Wellnessapp.entity.Meal;
import com.example.Wellnessapp.entity.Task;
import com.example.Wellnessapp.entity.User;
import com.example.Wellnessapp.service.TaskService;
import com.example.Wellnessapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    // Create Task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserbyJwtToken(jwt);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Task createdTask = taskService.createTask(taskRequest, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    // Get Task by ID
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) throws Exception {
        Task task = taskService.getTaskById(taskId);
        return ResponseEntity.ok(task);
    }
    //All Task
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Update Task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody TaskRequest taskRequest, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserbyJwtToken(jwt);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Task updatedTask = taskService.updateTask(taskId, taskRequest);
        if (updatedTask == null || !updatedTask.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(updatedTask);
    }

    // Delete Task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserbyJwtToken(jwt);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Task task = taskService.getTaskById(taskId);
        if (task == null || !task.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    // Get Tasks by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserbyJwtToken(jwt);

        if (user == null || !user.getId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Task> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestBody TaskStatusUpdate taskStatusUpdateRequest, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserbyJwtToken(jwt);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Task task = taskService.getTaskById(taskId);
        if (task == null || !task.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Task updatedTask = taskService.updateTaskStatus(taskId, taskStatusUpdateRequest);
        return ResponseEntity.ok(updatedTask);
    }

}


