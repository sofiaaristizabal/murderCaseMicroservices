package com.example.taskservice.task.dto;

import com.example.taskservice.task.model.Priority;
import com.example.taskservice.task.model.Status;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record TaskUpdateDTO(
        @NotBlank(message = "title is required") //Not blank is only for string
        @Size(min=2, max=120, message="title must be between 2 and 120 characters")
        String title,
        @NotBlank(message = "description is required") //Not blank is only for string
        @Size(min=2, max=300, message="description must be between 2 and 300 characters")
        String description,
        @NotNull(message = "priority is required")
        Priority priority,
        @NotNull(message = "start_date of birth is required")
        @Future(message = "start_date of birth must be in the past")
        LocalDate start_date,
        @NotNull(message = "investigatorId is required")
        @Positive(message = "investigatorId must be positive")
        Long investigatorId,
        @NotNull(message = "CaseId is required")
        @Positive(message = "CaseId must be positive")
        Long caseId
) {

}
