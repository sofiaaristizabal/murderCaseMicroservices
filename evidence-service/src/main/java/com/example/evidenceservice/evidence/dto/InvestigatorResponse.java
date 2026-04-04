package com.example.evidenceservice.evidence.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InvestigatorResponse(
        Long id,
        String name,
        Long caseId
) {
}
