package com.example.evidenceservice.evidence.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CaseResponse(
        Long id,
        String title,
        String status
) {
}
