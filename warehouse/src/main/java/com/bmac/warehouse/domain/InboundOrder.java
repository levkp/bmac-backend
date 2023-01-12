package com.bmac.warehouse.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class InboundOrder {
    private UUID id;
    private UUID batchId;
    private LocalDate date;
    private LocalDateTime received;
    private LocalDateTime shipped; // Todo, also add factory received
    private Map<UUID, Double> neededItems = new HashMap<>();

    public InboundOrder(UUID id, UUID batchId, LocalDate date, LocalDateTime received, Map<UUID, Double> neededItems) {
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

    public Map<UUID, Double> getNeededItems() {
        return neededItems;
    }
}
