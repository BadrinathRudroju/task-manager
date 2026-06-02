package com.badrinath.task_manager.service;

import com.badrinath.task_manager.dto.TaskRequest;
import com.badrinath.task_manager.dto.taskResponse;
import com.badrinath.task_manager.entity.Task;
import com.badrinath.task_manager.exception.TaskNotFoundException;
import com.badrinath.task_manager.mapper.TaskMapper;
import com.badrinath.task_manager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

//Business logic lies here
@Service
@Transactional
public class TaskService {
    final private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    public taskResponse findTaskById(Long id){
        Task retreivedTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return TaskMapper.toResponse(retreivedTask);
    }

    public taskResponse createTask(TaskRequest task){
        Task entityTask = TaskMapper.toEntity(task);
        Task savedTask =  taskRepository.save(entityTask);

        return TaskMapper.toResponse(savedTask);
    }

    public  taskResponse updateTaskById( Long id,TaskRequest updatedTask){
        Task task = taskRepository.findById(id)
                        .orElseThrow(() -> new TaskNotFoundException(id));

        TaskMapper.updateEntityFromRequest(task, updatedTask);

        return TaskMapper.toResponse(taskRepository.save(task));
    }

    public boolean deleteTaskById(Long id){
        Task task = taskRepository.findById(id).
                orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
        return true;
    }

    public List<Task> getTasksByCompletionStatus(boolean status){
        return taskRepository.findByCompleted(status);
    }

    public List<Task> SearchTasksByTitle(String title){
        return taskRepository.findByTitleIgnoreCase(title);
    }
}
