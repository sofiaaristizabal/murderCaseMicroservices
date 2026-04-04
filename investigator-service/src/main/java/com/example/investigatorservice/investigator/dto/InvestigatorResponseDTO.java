package com.example.investigatorservice.investigator.dto;

import com.example.investigatorservice.investigator.model.TypeDocument;

import java.time.LocalDate;

public record InvestigatorResponseDTO(
        Long id,
        String name,
        TypeDocument typeDocument,
        String document,
        LocalDate date_of_birth,
        String email,
        String keycloakId,
        Long caseId
) {
}
