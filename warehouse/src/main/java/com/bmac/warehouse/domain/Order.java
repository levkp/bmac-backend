package com.bmac.warehouse.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Order {
    private UUID id;
    private UUID batchId;
    private LocalDate date;
    private LocalDateTime received;
    private LocalDateTime shipped;
    private Map<Item, Double> orderLine = new HashMap<>();

    public Order(UUID id, UUID batchId, LocalDate date, LocalDateTime received) {
        this.id = id;
        this.batchId = batchId;
        this.date = date;
        this.received = received;
    }

    public UUID getId() {
        return id;
    }

    public UUID getBatchId() {
        return batchId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getReceived() {
        return received;
    }

    public LocalDateTime getShipped() {
        return shipped;
    }

    public Map<Item, Double> getOrderLine() {
        return orderLine;
    }
}
