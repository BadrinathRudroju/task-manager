package com.badrinath.task_manager.mapper;

import com.badrinath.task_manager.dto.TaskRequest;
import com.badrinath.task_manager.dto.taskResponse;
import com.badrinath.task_manager.entity.Task;
import org.springframework.stereotype.Component;


//separating your internal database structure from the data you expose to the
// outside world. They act as data translators, automatically converting
// entities (database models) into DTOs (Data Transfer Objects) and vice versa.
@Component
public class TaskMapper {

    public static Task toEntity(TaskRequest request){
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .completed(request.completed() != null ? request.completed() : false)
                .build();
    }

    public static taskResponse toResponse(Task task){
            return taskResponse.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .createdAt(task.getCreatedAt())
                    .completed(task.getCompleted())
                    .build();
    }

    public static void updateEntityFromRequest(Task task, TaskRequest request){
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setCompleted(request.completed());
    }
}
