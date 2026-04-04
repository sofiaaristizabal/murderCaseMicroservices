package com.example.investigatorservice.investigator.client;

import com.example.investigatorservice.investigator.dto.CaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "case-service")
public interface CaseClient {

    @GetMapping("/api/cases/{id}")
    CaseResponse getCaseById(@PathVariable Long id);
}
