package com.example.notificationservice.listeners;

import com.example.notificationservice.events.TaskAssignedEvent;
import com.example.notificationservice.events.TaskCompletedEvent;
import com.example.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskEventListener {

    private final NotificationService notificationService;

    @KafkaListener(topics = "task-assigned", groupId = "notification-group")
    public void handleTaskAssigned(TaskAssignedEvent event){
        log.info("Task with id: {} was assigned to investigator with id: {} and to case with id: {}", event.getTaskId(), event.getInvestigatorId(), event.getCaseId());
        notificationService.notifyTaskAssigned(event);
    }

    @KafkaListener(topics = "task-completed", groupId = "notification-group")
    public void handleTaskCompleted(TaskCompletedEvent event){
        log.info("Task with id: {} assigned to investigator with id: {} and to case with id: {} was completed on: {}", event.getTaskId(), event.getInvestigatorId(), event.getCaseId(), event.getOccurredAt());
        notificationService.notifyTaskCompleted(event);
    }
}
