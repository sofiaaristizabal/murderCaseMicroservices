package com.example.caseservice.Case.events;

import com.example.caseservice.Case.config.KafkaTopicConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaseEventProducer {

    public final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishCaseCreated(CaseCreatedEvent event){
        log.info("Publishing CaseCreated event for caseId: {}", event.getCaseId());
        kafkaTemplate.send(KafkaTopicConfig.CASE_CREATED_TOPIC, event.getCaseId().toString(), event);
    }
}
