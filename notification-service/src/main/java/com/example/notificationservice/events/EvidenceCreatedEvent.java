package com.example.notificationservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvidenceCreatedEvent {
    private Long evidenceId;
    private String title;
    private Long caseId;
    private LocalDateTime occurredAt;

}
