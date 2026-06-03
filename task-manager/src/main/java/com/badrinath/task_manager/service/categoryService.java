package com.badrinath.task_manager.service;

import com.badrinath.task_manager.entity.category;
import com.badrinath.task_manager.repository.categoryRepository;
import org.springframework.stereotype.Service;

@Service
public class categoryService {
    private categoryRepository categoryRepository;
    public void setCategoryRepository(categoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public category findByid(Long id){
        return categoryRepository.findById(id)
                .orElse(null);
    }

    public category create(category category){
        return categoryRepository.save(category);
    }
}
