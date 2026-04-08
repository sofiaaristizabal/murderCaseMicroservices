package com.example.taskservice.task.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskAssignedEvent {
    private Long taskId;
    private Long caseId;
    private Long investigatorId;
    private String title;
    private LocalDateTime occurredAt;
}
