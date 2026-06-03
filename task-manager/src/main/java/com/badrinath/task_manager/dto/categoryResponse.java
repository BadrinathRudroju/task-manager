package com.badrinath.task_manager.dto;

import lombok.Builder;

@Builder
public record categoryResponse(
        String name,
        Long categoryId,
        String description
) {
}
