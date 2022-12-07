package com.bmac.store.domain;

import com.bmac.common.domain.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class Order {
    private final UUID id;
    private Batch batch;
    private final LocalDateTime timestamp;

    private final Map<Product, Integer> orderLine;

    public Order(UUID id, Batch batch, LocalDateTime timestamp, Map<Product, Integer> orderLine) {
        this.id = id;
        this.batch = batch;
        this.timestamp = timestamp;
        this.orderLine = orderLine;
    }

    public Order(UUID id, LocalDateTime timestamp, Map<Product, Integer> orderLine) {
        this.id = id;
        this.timestamp = timestamp;
        this.orderLine = orderLine;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public UUID getId() {
        return id;
    }

    public Map<Product, Integer> getOrderLine() {
        return orderLine;
    }
}
