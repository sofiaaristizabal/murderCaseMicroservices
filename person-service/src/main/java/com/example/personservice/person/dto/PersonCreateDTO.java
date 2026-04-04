package com.example.personservice.person.dto;

import com.example.personservice.person.model.TypeDocument;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PersonCreateDTO(
        @NotBlank(message = "name is required") //Not blank is only for string
        @Size(min=2, max=120, message="name must be between 2 and 120 characters")
        String name,
        @NotNull(message = "type of document is required")
        TypeDocument typeDocument,
        @NotBlank(message = "Document is required")
        @Size(min=7, max=10)
        String document,
        @NotNull(message = "date of birth is required")
        @Past(message = "date of birth must be in the past")
        LocalDate date_of_birth,
        @NotBlank(message = "description is required")
        @Size(min=100, max=300, message="description must be between 100 and 300 characters")
        String description,
        @NotBlank(message = "address is required")
        @Size(min=10, max=50, message="address must be between 10 and 50 characters")
        String address,
        @NotBlank(message = "nationality is required")
        @Size(min=2, max=50, message="nationality must be between 2 and 50 characters")
        String nationality,
        @NotBlank(message = "contact info is required")
        @Size(min=2, max=200, message="contact info must be between 2 and 200 characters")
        String contact_info,
        @NotNull(message = "CaseId is required")
        @Positive(message = "CaseId must be positive")
        Long caseId
        ){

}
