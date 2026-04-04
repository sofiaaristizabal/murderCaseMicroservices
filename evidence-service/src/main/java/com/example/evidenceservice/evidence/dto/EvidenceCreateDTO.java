package com.example.evidenceservice.evidence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EvidenceCreateDTO (
        @NotBlank(message = "written evidence is required") //Not blank is only for string
        @Size(min=2, max=500, message="written evidence must be between 2 and 500 characters")
        String writtenEvidence,
        @NotBlank(message = "context is required") //Not blank is only for string
        @Size(min=2, max=200, message="context must be between 2 and 200 characters")
        String context,
        @NotBlank(message = "title is required") //Not blank is only for string
        @Size(min=2, max=120, message="title must be between 2 and 120 characters")
        String title,
        @NotBlank(message = "place where evidence was collected is required") //Not blank is only for string
        @Size(min=2, max=120, message="place where evidence was collected must be between 2 and 120 characters")
        String placeCollect,
        @NotNull(message = "CaseId is required")
        @Positive(message = "CaseId must be positive")
        Long caseId,
        @NotNull(message = "investigator who collected the evidence Id is required")
        @Positive(message = "investigator who collected the evidence Id must be positive")
        Long investigatorCollectId,
        @NotNull(message = "investigator who approved the evidence Id is required")
        @Positive(message = "investigator who approved the evidence Id must be positive")
        Long investigatorApprovedId

){
}
