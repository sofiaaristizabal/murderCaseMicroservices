package com.example.caseservice.Case.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CaseUpdateFinishDTO(
        @NotNull(message="Finish date is required")
        @Future(message = "finish_date of birth must be in the past")
        LocalDate finish_date
) {
}
