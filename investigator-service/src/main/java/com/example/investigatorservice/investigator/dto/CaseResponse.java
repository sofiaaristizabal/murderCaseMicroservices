package com.example.investigatorservice.investigator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //jackson, the spring booot library that converts JSON into java objects uses this annotation that tells it "if the json contains fields that are not in this class then ignore them
public record CaseResponse(
        Long id,
        String title,
        String status
) {
}
