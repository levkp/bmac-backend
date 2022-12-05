package com.bmac.store.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final Batch batch;
    private final LocalDateTime timestamp;

    private final Map<Product, Integer> orderLine;

    public Order(UUID id, Batch batch, LocalDateTime timestamp, Map<Product, Integer> orderLine) {
        this.id = id;
        this.batch = batch;
        this.timestamp = timestamp;
        this.orderLine = orderLine;
    }

    public Batch getBatch() {
        return batch;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void addProduct(Product product, int amount) {
        orderLine.put(product, amount);
    }
}
