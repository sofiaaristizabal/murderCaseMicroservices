package com.example.taskservice.task.client;

import com.example.taskservice.task.dto.InvestigatorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="investigator-service")
public interface InvestigatorClient {
    @GetMapping("/api/investigators/{id}")
    InvestigatorResponse getInvestigatorById(@PathVariable Long id);
}
