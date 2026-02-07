package com.toliveira.shopofto.kafka.consumer;

import com.toliveira.shopofto.kafka.event.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);

    // @KafkaListener is the magic annotation.
    // It runs in a background thread and polls the broker.
    @KafkaListener(topics = "sales.orders", groupId = "notification-group")
    public void handleOrderCreated(OrderCreatedEvent event) {
        // 1. Log reception (Structured Logging)
        log.info("Received Event: eventId={}, orderId={}, customerId={}",
                "evt-" + event.orderId(), // mocking an event ID for now
                event.orderId(),
                event.customerId());

        // 2. Simulate Business Logic (Sending Email)
        sendEmail(event);
    }

    private void sendEmail(OrderCreatedEvent event) {
        log.info("📧 Sending confirmation email to Customer {} for Order {} worth ${}",
                event.customerId(),
                event.orderId(),
                event.totalAmount());

        // In a real app, you would call an SMTP server or SendGrid here.
    }
}