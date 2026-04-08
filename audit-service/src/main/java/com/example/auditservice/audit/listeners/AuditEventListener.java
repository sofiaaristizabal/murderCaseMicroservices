package com.example.auditservice.audit.listeners;

import com.example.auditservice.audit.events.CaseCreatedEvent;
import com.example.auditservice.audit.events.EvidenceCreatedEvent;
import com.example.auditservice.audit.events.TaskAssignedEvent;
import com.example.auditservice.audit.events.TaskCompletedEvent;
import com.example.auditservice.audit.model.AuditLog;
import com.example.auditservice.audit.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuditEventListener {

    private final AuditLogRepository auditLogRepository;

    @KafkaListener(topics = "case-created", groupId="audit-group")
    public void handleCaseCreated(CaseCreatedEvent event){
        log.info("Auditing caseCreated event with case id: {}", event.getCaseId());
        auditLogRepository.save(AuditLog.builder()
                .eventType("CASE_CREATED")
                .entityType("CASE")
                .entityId(event.getCaseId())
                .relatedCaseId(event.getCaseId())
                .description("Case created: " + event.getTitle())
                .occurredAt(event.getOccurredAt())
                .recordedAt(LocalDateTime.now())
                .build());
    }

    @KafkaListener(topics = "evidence-created", groupId="audit-group")
    public void handleEvidenceCreated(EvidenceCreatedEvent event){
        log.info("Auditing evidenceCreated event with evidence id: {}", event.getEvidenceId());
        auditLogRepository.save(AuditLog.builder()
                .eventType("EVIDENCE_CREATED")
                .entityType("EVIDENCE")
                .entityId(event.getEvidenceId())
                .relatedCaseId(event.getCaseId())
                .description("Evidence created: " + event.getTitle())
                .occurredAt(event.getOccurredAt())
                .recordedAt(LocalDateTime.now())
                .build());
    }

    @KafkaListener(topics = "task-assigned", groupId="audit-group")
    public void handleTaskAssigned(TaskAssignedEvent event){
        log.info("Auditing taskAssigned event with task id: {}, and investigator id: {}", event.getTaskId(), event.getInvestigatorId());
        auditLogRepository.save(AuditLog.builder()
                .eventType("TASK_ASSIGNED")
                .entityType("TASK")
                .entityId(event.getTaskId())
                .relatedCaseId(event.getCaseId())
                .description("Task assigned: " + event.getTitle())
                .occurredAt(event.getOccurredAt())
                .recordedAt(LocalDateTime.now())
                .build());
    }

    @KafkaListener(topics = "task-completed", groupId="audit-group")
    public void handleTaskComplete(TaskCompletedEvent event){
        log.info("Auditing taskCompleted event with task id: {}, and investigator id: {}, completed at: {}", event.getTaskId(), event.getInvestigatorId(), event.getOccurredAt());
        auditLogRepository.save(AuditLog.builder()
                .eventType("TASK_COMPLETED")
                .entityType("TASK")
                .entityId(event.getTaskId())
                .relatedCaseId(event.getCaseId())
                .description("Task completed: " + event.getTitle())
                .occurredAt(event.getOccurredAt())
                .recordedAt(LocalDateTime.now())
                .build());
    }

}
