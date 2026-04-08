package com.example.notificationservice.listeners;

import com.example.notificationservice.events.EvidenceCreatedEvent;
import com.example.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EvidenceEventListener {

    private final NotificationService notificationService;

    @KafkaListener(topics = "evidence-created", groupId = "notification-group")
    public void handleEvidenceCreated(EvidenceCreatedEvent event){
        log.info("EvidenceCreated event receives for evidence with id: {} and case with id: {}", event.getEvidenceId(), event.getCaseId());
        notificationService.notifyEvidenceCreated(event);
    }
}
