package com.badrinath.task_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//dto - It is a simple object that holds data to be sent across the network.
//A DTO contains only fields, getters, setters, or constructors
//It has absolutely zero business logic or database annotations

//A request is a specific type of DTO that travels from client to the server.
//it represents the data user is submitting
public record TaskRequest(
            @Size(min = 1, max = 100, message = "title should be between 1-100 characters")
            @NotBlank
            String title,

            @Size(min = 3, max = 100, message =  "description should be between 1-100 characters")
            String description,
            Boolean completed
) {
}
