package com.example.taskservice.task.events;

import com.example.taskservice.task.config.KafkaTopicConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskEventProducer {

    public final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishTaskAssigned(TaskAssignedEvent event){
        log.info("Publishing taskAssigned event for taskId: {}", event.getTaskId());
        kafkaTemplate.send(KafkaTopicConfig.TASK_ASSIGNED_TOPIC, event.getTaskId().toString(), event);
    }

    public void publishTaskCompleted(TaskCompletedEvent event){
        log.info("Publishing taskCompleted event for taskId: {}", event.getTaskId());
        kafkaTemplate.send(KafkaTopicConfig.TASK_COMPLETED_TOPIC, event.getTaskId().toString(), event);
    }
}
