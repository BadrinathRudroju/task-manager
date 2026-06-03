package com.badrinath.task_manager.controller;

import com.badrinath.task_manager.entity.category;
import com.badrinath.task_manager.repository.categoryRepository;
import com.badrinath.task_manager.service.categoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class categoryController {
    private final categoryService categoryService;

    public categoryController(categoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public category findByid(Long id) {
        return categoryService.findByid(id);
    }

    @PostMapping
    public ResponseEntity<category> create(
            @RequestBody category category) {
        category createCategory = categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCategory);
    }
}
