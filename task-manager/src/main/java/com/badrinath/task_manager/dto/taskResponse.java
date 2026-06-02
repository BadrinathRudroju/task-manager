package com.badrinath.task_manager.dto;

import lombok.Builder;

import java.time.LocalDateTime;

//A response is specific type of DTO that travels from the server to the client
@Builder
public record taskResponse(
        Long id,
        String title,
        String description,
        Boolean completed,
        LocalDateTime createdAt
) {
}
