package com.badrianath.task_manager.service;

import com.badrianath.task_manager.Entity.Task;
import com.badrianath.task_manager.repository.TaskRepository;
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

    public Optional<Task> findTaskById(Long id){
        return taskRepository.findById(id);
    }

    public Task createTask( Task task){
        return taskRepository.save(task);
    }

    public  Optional<Task> updateTaskById( Long id,Task updatedTask){
        return taskRepository.findById(id)
                .map(task -> {
                    updatedTask.setCompleted(updatedTask.getCompleted());
                    updatedTask.setCreatedAt(updatedTask.getCreatedAt());
                    updatedTask.setCompleted(updatedTask.getCompleted());
                    return taskRepository.save(task);
                });
    }

    public boolean deleteTaskById(Long id){
        return taskRepository.findById(id)
                .map(Task -> {
                    taskRepository.delete(Task);
                    return true;
                })
                .orElse(false);
    }

    public List<Task> getTasksByCompletionStatus(boolean status){
        return taskRepository.findByCompleted(status);
    }

    public List<Task> SearchTasksByTitle(String title){
        return taskRepository.findByTitleIgnoreCase(title);
    }
}
