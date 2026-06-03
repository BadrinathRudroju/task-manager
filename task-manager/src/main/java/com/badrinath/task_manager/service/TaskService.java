package com.badrinath.task_manager.service;

import com.badrinath.task_manager.dto.TaskRequest;
import com.badrinath.task_manager.dto.taskResponse;
import com.badrinath.task_manager.entity.Task;
import com.badrinath.task_manager.exception.TaskNotFoundException;
import com.badrinath.task_manager.mapper.TaskMapper;
import com.badrinath.task_manager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public Page<Task> findAllTasks(Pageable pageable){
        return taskRepository.findAll(pageable);
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

    public List<taskResponse> getTasksByCompletionStatus(boolean status){
        final List<Task> completedTasks = taskRepository.findByCompleted(status);
        return completedTasks.stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

    public Page<taskResponse> getTasksByCompletionStatus(boolean status, Pageable pageable) {
        final Page<Task> completedTasks = taskRepository.findByCompleted(status, pageable);
        return completedTasks.map(TaskMapper::toResponse);


    }

    public Page<Task> searchTaskByTitle(String title, Pageable pageable) {
        return taskRepository.findByTitleContainingIgnoreCase(title,pageable);
    }

    public Page<Task> searchTaskByTitleAndCompletion(
            String title,
            Boolean completed,
            Pageable pageable) {

        return taskRepository.findByTitleContainingAndCompleted(title,completed,pageable);
    }

    public Page<Task> getTasksByCompletion(Boolean completed, Pageable pageable) {
        return taskRepository.findByCompleted(completed, pageable);
    }
}
