package com.bmac.store.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private final Batch batch;
    private final LocalDateTime timestamp;
    private final Product product;
    private final UUID uuid;
    private final double amount;

    public Order(Batch batch, LocalDateTime timestamp, Product product, UUID uuid, double amount) {
        this.batch = batch;
        this.timestamp = timestamp;
        this.product = product;
        this.uuid = uuid;
        this.amount = amount;
    }

    public Order(Batch batch, Product product, double amount) {
        this.batch = batch;
        this.timestamp = LocalDateTime.now();
        this.product = product;
        this.uuid = UUID.randomUUID();
        this.amount = amount;
    }

    public Batch getBatch() {
        return batch;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Product getProduct() {
        return product;
    }

    public UUID getUuid() {
        return uuid;
    }

    public double getAmount() {
        return amount;
    }
}
