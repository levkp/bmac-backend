package com.bmac.store.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Order {
    private final Batch batch;
    private final LocalDateTime timestamp;
    private final Product product;
    private final UUID uuid;
    private final double amount;

    public Order(Batch batch, Product product, double amount) {
        this.batch = batch;
        this.timestamp = LocalDateTime.now();
        this.product = product;
        this.uuid = UUID.randomUUID();
        this.amount = amount;
    }
}
