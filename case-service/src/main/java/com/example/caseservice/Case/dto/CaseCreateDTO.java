package com.example.caseservice.Case.dto;

import com.example.caseservice.Case.model.Status;
import com.example.caseservice.Case.model.Priority;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CaseCreateDTO (
        @NotBlank(message = "title is required") //Not blank is only for string
        @Size(min=2, max=120, message="title must be between 2 and 120 characters")
        String title,
        @NotBlank(message = "description is required") //Not blank is only for string
        @Size(min=2, max=300, message="description must be between 2 and 300 characters")
        String description,
        @NotNull(message = "start_date of birth is required")
        @Future(message = "start_date of birth must be in the past")
        LocalDate start_date,
        @NotNull(message = "status is required") //Not blank is only for string
        Status status,
        @NotNull(message = "priority is required")
        Priority priority
){
}
