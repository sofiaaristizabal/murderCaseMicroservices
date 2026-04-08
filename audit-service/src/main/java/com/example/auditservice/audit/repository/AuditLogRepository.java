package com.example.auditservice.audit.repository;

import com.example.auditservice.audit.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

        List<AuditLog>  findByRelatedCaseIdOrderByOccurredAtDesc(Long relatedCaseId);

        List<AuditLog> findAllByOrderByRecordedAtDesc();
}
