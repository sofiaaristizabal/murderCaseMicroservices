package com.example.taskservice.task.dto;

public record CaseResponse(
        Long id,
        String title,
        String status
) {
}
