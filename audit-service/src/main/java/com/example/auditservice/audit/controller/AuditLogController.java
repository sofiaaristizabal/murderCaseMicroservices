package com.example.auditservice.audit.controller;

import com.example.auditservice.audit.model.AuditLog;
import com.example.auditservice.audit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogRepository auditLogRepository;

    @GetMapping
    public List<AuditLog> getAllAuditLogs(){
        return auditLogRepository.findAllByOrderByRecordedAtDesc();
    }

    @GetMapping("/case/{caseId}")
    public List<AuditLog> getAuditForCase(@PathVariable Long caseId){
        return auditLogRepository.findByRelatedCaseIdOrderByOccurredAtDesc(caseId);
    }
}
