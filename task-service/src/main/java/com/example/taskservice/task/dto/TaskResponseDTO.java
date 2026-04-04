package com.example.taskservice.task.dto;

import com.example.taskservice.task.model.Priority;
import com.example.taskservice.task.model.Status;

import java.time.LocalDate;

public record TaskResponseDTO(
    Long id,
    String title,
    String description,
    Status status,
    Priority priority,
    LocalDate start_date,
    LocalDate finish_date,
    Long investigatorId,
    Long caseId
) {
}
