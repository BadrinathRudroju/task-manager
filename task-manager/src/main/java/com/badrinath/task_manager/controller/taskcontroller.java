package com.badrinath.task_manager.controller;

import com.badrinath.task_manager.dto.TaskRequest;
import com.badrinath.task_manager.dto.taskResponse;
import com.badrinath.task_manager.entity.Task;
import com.badrinath.task_manager.mapper.TaskMapper;
import com.badrinath.task_manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Api/v1/tasks")
public class taskcontroller {

    final private TaskService taskService;
    final private TaskMapper taskMapper;

    public taskcontroller(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

//    @GetMapping("TasksList")
//    public List<Task> getAllTasks(){
//        return taskService.findAllTasks();
//    }

    @GetMapping("/search")
    public ResponseEntity<Map<String , Object>> search(
            @RequestParam(required = false ) String title,
            @RequestParam(required = false ) Boolean completed,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC")  String sortDir
    ){

        Sort sort = sortDir.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Task> taskPage;

        if(title != null && completed != null){
            taskPage = taskService.searchTaskByTitleAndCompletion(
                    title , completed , pageable
            );
        }else if(title != null){
            taskPage = taskService.searchTaskByTitle(title , pageable);
        }else if(completed != null){
            taskPage = taskService.getTasksByCompletion(completed, pageable);
        }else{
            taskPage = taskService.findAllTasks(pageable);
        }

        List<taskResponse> taskList = taskPage.getContent()
                .stream()
                .map(task -> new taskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCompleted(),
                        task.getCreatedAt(),
                        null
                )).toList();

        Map<String , Object> map = new HashMap<>();
        map.put("tasks", taskList);
        map.put("currentPage", taskPage.getNumber());
        map.put("totalPages", taskPage.getTotalPages());
        map.put("totalElements", taskPage.getTotalElements());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTasks(
            @RequestParam(defaultValue =  "0") int page,
            @RequestParam(defaultValue= "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC")  String sortDir
            ){

        Sort sort = sortDir.equalsIgnoreCase("ASC")?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Task> taskPage = taskService.findAllTasks(pageable);

        List<taskResponse> taskList = taskPage.getContent()
                .stream()
                .map( task -> new taskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getCompleted(),
                        task.getCreatedAt(),
                        null))
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("tasks", taskList);
        response.put("currentPage", taskPage.getNumber());
        response.put("totalPages", taskPage.getTotalPages());
        response.put("totalElements", taskPage.getTotalElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
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
    public List<taskResponse> getCompletedTasksByStatus(@PathVariable boolean status){
        return taskService.getTasksByCompletionStatus(status);
    }

//    @GetMapping("/search")
//    public List<Task> searchTaskByTitle(@RequestParam String title){
//        return taskService.SearchTasksByTitle(title);
//    }
}
