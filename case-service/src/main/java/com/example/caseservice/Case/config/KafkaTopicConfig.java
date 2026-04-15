package com.example.caseservice.Case.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaTopicConfig {

    public static final String CASE_CREATED_TOPIC = "case-created";

    @Bean
    public NewTopic caseCreatedTopic(){
        return TopicBuilder.name(CASE_CREATED_TOPIC).partitions(1).replicas(1).build();
    }
}
