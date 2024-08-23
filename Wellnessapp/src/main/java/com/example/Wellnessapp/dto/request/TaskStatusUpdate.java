package com.example.Wellnessapp.dto.request;

import com.example.Wellnessapp.entity.TaskStatus;
import lombok.Data;

@Data
public class TaskStatusUpdate {
    private TaskStatus status;
}
