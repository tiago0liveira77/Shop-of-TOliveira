package com.toliveira.shopofto.controller;

import com.toliveira.shopofto.domain.Order;
import com.toliveira.shopofto.kafka.event.OrderCreatedEvent;
import com.toliveira.shopofto.kafka.producer.OrderProducer;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public String createOrder(@RequestBody Order orderRequest) {
        // 1. Simulate saving to DB (skipping Repository for brevity in this step)
        orderRequest.setId(UUID.randomUUID().toString());
        orderRequest.setCreatedAt(LocalDateTime.now());
        orderRequest.setStatus("CREATED");

        // 2. Map Entity to Event
        OrderCreatedEvent event = new OrderCreatedEvent(
                orderRequest.getId(),
                orderRequest.getCustomerId(),
                orderRequest.getTotalAmount(),
                LocalDateTime.now()
        );

        // 3. Publish Event
        orderProducer.publishOrderCreated(event);

        return "Order placed successfully! ID: " + orderRequest.getId();
    }
}