package com.bmac.store.domain;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class StoreOrder {
    private final UUID id;
    private Batch batch;
    private final LocalDateTime timestamp;
    private Map<StoreProduct, Integer> orderedProducts;

    public StoreOrder(UUID id, Batch batch, LocalDateTime timestamp, Map<StoreProduct, Integer> orderLine) {
        this.id = id;
        this.batch = batch;
        this.timestamp = timestamp;
        this.orderedProducts = orderLine;
    }

    public StoreOrder(UUID id, LocalDateTime timestamp, Map<StoreProduct, Integer> orderLine) {
        this.id = id;
        this.timestamp = timestamp;
        this.orderedProducts = orderLine;
    }

    public StoreOrder(UUID id, Batch batch, LocalDateTime timestamp) {
        this.id = id;
        this.batch = batch;
        this.timestamp = timestamp;
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

    public Map<StoreProduct, Integer> getOrderedProducts() {
        return orderedProducts;
    }

    public Map<UUID, Integer> getOrderedProductsWithUUIDKey() {
        return this.orderedProducts.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        k -> k.getKey().getId(),
                        Map.Entry::getValue
                ));
    }
}
