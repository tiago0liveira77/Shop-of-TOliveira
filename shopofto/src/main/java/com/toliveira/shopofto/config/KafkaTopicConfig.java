package com.toliveira.shopofto.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name("sales.orders")
                .partitions(3)
                .replicas(1) // Only 1 broker in our Docker setup
                .build();
    }
}