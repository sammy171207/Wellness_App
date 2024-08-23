package com.example.Wellnessapp.dto.request;

import com.example.Wellnessapp.entity.TaskPriority;
import com.example.Wellnessapp.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private TaskStatus status;
    private TaskPriority priority;
    private Long userId;
}
