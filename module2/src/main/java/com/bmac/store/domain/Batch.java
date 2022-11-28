package com.bmac.store.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Batch {
    private final UUID uuid;
    private final LocalDate date;

    public Batch(UUID uuid, LocalDate date) {
        this.date = date;
        this.uuid = uuid;
    }
}
