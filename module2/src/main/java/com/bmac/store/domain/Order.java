package com.bmac.store.domain;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class Order {
    private final LocalDateTime timestamp;
    private final Map<Product, Integer> orderLine;
    private final UUID uuid;
    private OrderStatus status;

    // Todo: MVP only: order must have a customer
    public Order() {
        uuid = UUID.randomUUID();
        timestamp = LocalDateTime.now();
        orderLine = new HashMap<>();
        status = OrderStatus.RECEIVED;
    }
}
