package com.example.personservice.person.dto;

import com.example.personservice.person.model.TypeDocument;

import java.lang.invoke.TypeDescriptor;
import java.time.LocalDate;

public record PersonRespuestaDTO (
        Long id,
        String name,
        TypeDocument typeDocument,
        String document,
        LocalDate date_of_birth,
        String description,
        String address,
        String nationality,
        String contact_info,
        Long caseId
){


}
