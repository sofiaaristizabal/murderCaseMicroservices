package com.example.taskservice.task.client;

import com.example.taskservice.task.dto.CaseResponse;
import jakarta.ws.rs.PathParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="case-service")
public interface CaseClient {

    @GetMapping("/api/cases/{id}")
    CaseResponse getCaseById(@PathVariable Long id);
}
