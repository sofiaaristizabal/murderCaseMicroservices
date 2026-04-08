package com.example.auditservice.audit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditsLogs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private String entityType;

    @Column(nullable = false)
    private Long entityId;

    @Column(nullable = false)
    private Long relatedCaseId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime occurredAt;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

}
