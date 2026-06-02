package com.badrinath.task_manager.controller;

import com.badrinath.task_manager.dto.TaskRequest;
import com.badrinath.task_manager.dto.taskResponse;
import com.badrinath.task_manager.entity.Task;
import com.badrinath.task_manager.mapper.TaskMapper;
import com.badrinath.task_manager.repository.TaskRepository;
import com.badrinath.task_manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api/v1/tasks")
public class taskcontroller {

    final private TaskService taskService;
    final private TaskMapper taskMapper;

    public taskcontroller(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("TasksList")
    public List<Task> getAllTasks(){
        return taskService.findAllTasks();
    }

    @GetMapping("/{id}")
    public taskResponse getTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id);

    }

    @PostMapping
    public ResponseEntity<taskResponse> createTask(@Valid @RequestBody TaskRequest task){
        taskResponse savedTask  = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{id}")
    public taskResponse updateTask(@PathVariable Long id,
                           @Valid @RequestBody TaskRequest updatedTask){
        return taskService.updateTaskById(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
       taskService.deleteTaskById(id);
       return ResponseEntity.ok().build();
    }

    @GetMapping("/completed/{status}")
    public List<Task> getCompletedTasksByStatus(@PathVariable boolean status){
        return taskService.getTasksByCompletionStatus(status);
    }

    @GetMapping("/search")
    public List<Task> searchTaskByTitle(@RequestParam String title){
        return taskService.SearchTasksByTitle(title);
    }
}
