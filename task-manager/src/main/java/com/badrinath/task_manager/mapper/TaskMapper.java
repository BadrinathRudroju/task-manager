package com.badrinath.task_manager.mapper;

import com.badrinath.task_manager.dto.TaskRequest;
import com.badrinath.task_manager.dto.categoryResponse;
import com.badrinath.task_manager.dto.taskResponse;
import com.badrinath.task_manager.entity.Task;
import com.badrinath.task_manager.entity.category;
import com.badrinath.task_manager.service.TaskService;
import com.badrinath.task_manager.service.categoryService;
import org.springframework.stereotype.Component;


//separating your internal database structure from the data you expose to the
// outside world. They act as data translators, automatically converting
// entities (database models) into DTOs (Data Transfer Objects) and vice versa.
@Component
public class TaskMapper {

    private static categoryService categoryService;

    TaskMapper(categoryService categoryService) {
        this.categoryService = categoryService;
    }

    public static Task toEntity(TaskRequest request){

        category category = null;

        if(request!=null && request.categoryId() !=null){
            category = categoryService.findByid(request.categoryId());
        }
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .completed(request.completed() != null ? request.completed() : false)
                .category(category)
                .build();
    }

    public static taskResponse toResponse(Task task){
        categoryResponse categoryResponse = null;

        if(task!=null && task.getCategory() !=null){
            categoryResponse = categoryResponse.builder()
                    .categoryId(task.getCategory().getId())
                    .name(task.getCategory().getName())
                    .description(task.getCategory().getDescription())
                    .build();
        }
            return taskResponse.builder()
                    .id(task.getId())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .createdAt(task.getCreatedAt())
                    .completed(task.getCompleted())
                    .category(categoryResponse)
                    .build();
    }

    public static void updateEntityFromRequest(Task task, TaskRequest request){
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setCompleted(request.completed());

        categoryResponse categoryResponse = null;

        if(request.categoryId() !=null){
            task.getCategory().setId(request.categoryId());
        }
    }
}
