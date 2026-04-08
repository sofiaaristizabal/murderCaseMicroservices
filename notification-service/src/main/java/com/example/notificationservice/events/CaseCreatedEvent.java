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
public class CaseCreatedEvent {
    private Long caseId;
    private String title;
    private String status;
    private LocalDateTime occurredAt;
}
