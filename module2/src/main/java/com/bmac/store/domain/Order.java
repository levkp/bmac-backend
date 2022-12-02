package com.bmac.store.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final Batch batch;
    private final LocalDateTime timestamp;

    private final Map<Product, Integer> product = new HashMap<>();

    public Order(Batch batch) {
        this.batch = batch;
        this.timestamp = LocalDateTime.now();
        this.id = UUID.randomUUID();
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

}
