package com.example.taskservice.task.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaTopicConfig {

    public static final String TASK_ASSIGNED_TOPIC = "task-assigned";
    public static final String TASK_COMPLETED_TOPIC = "task-completed";

    @Bean
    public NewTopic taskAssignedTopic(){
        return TopicBuilder.name(TASK_ASSIGNED_TOPIC).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic taskCompletedTopic(){
        return TopicBuilder.name(TASK_COMPLETED_TOPIC).partitions(1).replicas(1).build();
    }
}
