package com.example.notificationservice.listeners;

import com.example.notificationservice.events.CaseCreatedEvent;
import com.example.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CaseEventListener {

    private final NotificationService notificationService;

    @KafkaListener(topics = "case-created", groupId = "notification-group")
    public void handleCaseCreated(CaseCreatedEvent event){
        log.info("CaseCreated event received fo case: {}", event.getCaseId());
        notificationService.notifyCaseCreated(event);
    }
}
