package com.toliveira.shopofto.kafka.producer;

import com.toliveira.shopofto.kafka.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderCreated(OrderCreatedEvent event) {
        System.out.println("Publishing Order Created Event: " + event.orderId());

        // TOPIC NAME: "sales.orders"
        // KEY: event.orderId() -> VERY IMPORTANT for ordering!
        // VALUE: event -> The JSON payload
        kafkaTemplate.send("sales.orders", event.orderId(), event);
    }
}