package com.example.evidenceservice.evidence.dto;

public record EvidenceResponseDTO(
        Long id,
        String writtenEvidence,
        String context,
        String title,
        String placeCollect,
        Long caseId,
        Long investigatorCollectId,
        Long investigatorApprovedId
) {
}
