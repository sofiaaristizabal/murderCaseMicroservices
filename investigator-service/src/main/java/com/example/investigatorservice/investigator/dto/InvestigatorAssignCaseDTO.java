package com.example.investigatorservice.investigator.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InvestigatorAssignCaseDTO (
        @NotNull(message = "CaseId is required")
        @Positive(message = "CaseId must be positive")
        Long caseId
){
}
