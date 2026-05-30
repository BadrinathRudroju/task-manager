package com.badrianath.task_manager.controller;

import com.badrianath.task_manager.Entity.Task;
import com.badrianath.task_manager.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Api/v1/tasks")
public class taskcontroller {

    final private TaskRepository taskRepository;

    public taskcontroller(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping("TasksList")
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task savedTask  = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask){
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setCreatedAt(updatedTask.getCreatedAt());
                    task.setCompleted(updatedTask.getCompleted());
                    Task updateTask = taskRepository.save(task);
                    return ResponseEntity.ok(updateTask);
                }).
                orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        return taskRepository.findById(id).
                map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().<Void>build();
                }).
                orElse(ResponseEntity.notFound().build());
    }
}
