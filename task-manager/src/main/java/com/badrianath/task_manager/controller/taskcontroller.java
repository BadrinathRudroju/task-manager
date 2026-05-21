package com.badrianath.task_manager.controller;

import com.badrianath.task_manager.Entity.Task;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Api/v1/tasks")
public class taskcontroller {


    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping("TasksList")
    public List<Task> getAllTasks(){
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        task.setId(nextId++);
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);
        tasks.add(task);

        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask){
        for(int i=0; i<tasks.size(); i++){
            Task task = tasks.get(i);
            if(task.getId().equals(id)){
                updatedTask.setId(id);
                updatedTask.setCreatedAt(task.getCreatedAt());
                tasks.set(i,updatedTask);
                return updatedTask;
            }
        }
        return null;
    }

    @DeleteMapping
    public void deleteTask(@PathVariable Long id){
        tasks.removeIf(task -> task.getId().equals(id));
    }
}
