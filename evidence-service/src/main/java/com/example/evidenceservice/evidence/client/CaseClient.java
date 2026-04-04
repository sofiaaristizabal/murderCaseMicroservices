package com.example.evidenceservice.evidence.client;

import com.example.evidenceservice.evidence.dto.CaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="case-service")
public interface CaseClient {

    @GetMapping("/api/cases/{id}")
    CaseResponse getCaseById(@PathVariable Long id);
}
