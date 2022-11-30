package com.bmac.store.domain;


import java.time.LocalDate;
import java.util.UUID;

public class Batch {
    private final UUID uuid;
    private final LocalDate date;

    public Batch(UUID uuid, LocalDate date) {
        this.date = date;
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDate getDate() {
        return date;
    }
}
