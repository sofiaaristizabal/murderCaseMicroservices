package com.example.evidenceservice.evidence.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaTopicConfig {

    public static final String EVIDENCE_CREATED_TOPIC = "evidence-created";

    @Bean
    public NewTopic evidenceCreatedTopic(){
        return TopicBuilder.name(EVIDENCE_CREATED_TOPIC).partitions(1).replicas(1).build();
    }
}
