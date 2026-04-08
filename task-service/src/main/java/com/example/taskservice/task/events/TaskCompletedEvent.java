package com.example.taskservice.task.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCompletedEvent {
    private Long taskId;
    private Long caseId;
    private Long investigatorId;
    private String title;
    private LocalDate startedAt;
    private LocalDateTime occurredAt;
}
