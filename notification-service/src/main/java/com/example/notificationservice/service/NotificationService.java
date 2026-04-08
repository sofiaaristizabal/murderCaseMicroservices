package com.example.notificationservice.service;

import com.example.notificationservice.events.CaseCreatedEvent;
import com.example.notificationservice.events.EvidenceCreatedEvent;
import com.example.notificationservice.events.TaskAssignedEvent;
import com.example.notificationservice.events.TaskCompletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.Java;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    private final EmailNotificationService emailService;

    public NotificationService(EmailNotificationService emailService){
        this.emailService = emailService;
    }

    public void notifyCaseCreated(CaseCreatedEvent event){
        String message = String.format(
                "New Evidence created\n" +
                "ID: %d\n" +
                "Title: %s\n" +
                "Status: %s\n" +
                "Time: %s\n",
                event.getCaseId(),
                event.getTitle(),
                event.getStatus(),
                event.getOccurredAt()
        );

        String subject = String.format("Case with id %d was created successfully", event.getCaseId());

        emailService.send(subject, message);

    }

    public void notifyEvidenceCreated(EvidenceCreatedEvent event){
        String message = String.format(
                "New Evidence created\n" +
                        "ID: %d\n" +
                        "Title: %s\n" +
                        "caseId: %d\n" +
                        "Time: %s\n",
                event.getEvidenceId(),
                event.getTitle(),
                event.getCaseId(),
                event.getOccurredAt()
        );

        String subject = String.format("Evidence with id %d was created successfully", event.getCaseId());

        emailService.send(subject, message);

    }

    public void notifyTaskAssigned(TaskAssignedEvent event){
        String message = String.format(
                "New Task Assigned\n" +
                        "ID: %d\n" +
                        "caseId: %s\n" +
                        "investigatorId: %s\n" +
                        "Title: %s\n" +
                        "Time: %s\n",
                event.getTaskId(),
                event.getCaseId(),
                event.getInvestigatorId(),
                event.getTitle(),
                event.getOccurredAt()
        );

        String subject = String.format("Task with id %d was assigned successfully", event.getCaseId());

        emailService.send(subject, message);

    }

    public void notifyTaskCompleted(TaskCompletedEvent event){
        String message = String.format(
                "Tas with id: %d was completed\n" +
                        "ID: %d\n" +
                        "caseId: %s\n" +
                        "investigatorId: %s\n" +
                        "Title: %s\n" +
                        "Start date: %s\n" +
                        "Time: %s\n",
                event.getTaskId(),
                event.getTaskId(),
                event.getCaseId(),
                event.getInvestigatorId(),
                event.getTitle(),
                event.getStartedAt(),
                event.getOccurredAt()
        );

        String subject = String.format("Case with id %d was created successfully", event.getCaseId());

        emailService.send(subject, message);

    }
}
