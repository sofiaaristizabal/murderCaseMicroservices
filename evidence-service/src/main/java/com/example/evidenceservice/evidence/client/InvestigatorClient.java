package com.example.evidenceservice.evidence.client;

import com.example.evidenceservice.evidence.dto.InvestigatorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="investigator-service")
public interface InvestigatorClient {
    @GetMapping("/api/investigators/{id}")
    InvestigatorResponse getInvestigatorById(@PathVariable Long id);
}
