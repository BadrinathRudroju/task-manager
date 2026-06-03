package com.badrinath.task_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    private Boolean completed = false;

    @Column(name = "created_at",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinTable(name = "category_id")
    private category category;

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}
