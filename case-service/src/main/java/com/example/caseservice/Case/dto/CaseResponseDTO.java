package com.example.caseservice.Case.dto;

import com.example.caseservice.Case.model.Status;
import com.example.caseservice.Case.model.Priority;

import java.time.LocalDate;

public record CaseResponseDTO(
        Long id,
        String title,
        String description,
        LocalDate start_date,
        LocalDate finish_date,
        Status status,
        Priority priority

) {
}
