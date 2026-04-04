package com.example.investigatorservice.investigator.dto;

import com.example.investigatorservice.investigator.model.TypeDocument;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record InvestigatorUpdateDTO (
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
        LocalDate date_of_birth
){
}
