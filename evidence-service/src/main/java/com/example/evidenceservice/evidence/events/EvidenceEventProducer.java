package com.example.evidenceservice.evidence.events;

import com.example.evidenceservice.evidence.config.KafkaTopicConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EvidenceEventProducer {

    public final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishEvidenceCreated(EvidenceCreatedEvent event){
        log.info("Publishing evidence created for EvidenceId: {}", event.getEvidenceId());
        kafkaTemplate.send(KafkaTopicConfig.EVIDENCE_CREATED_TOPIC, event.getEvidenceId().toString(), event);

    }

}
