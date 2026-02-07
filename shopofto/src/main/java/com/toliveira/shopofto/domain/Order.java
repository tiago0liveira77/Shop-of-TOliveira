package com.toliveira.shopofto.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long customerId;
    private BigDecimal totalAmount;
    private String status; // CREATED, SENT, etc.
    private LocalDateTime createdAt;
}