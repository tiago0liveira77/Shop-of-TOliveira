package com.toliveira.shopofto.kafka.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Using Java Records for immutability (perfect for events)
public record OrderCreatedEvent(
        String orderId,
        Long customerId,
        BigDecimal totalAmount,
        LocalDateTime occurredAt
) {}