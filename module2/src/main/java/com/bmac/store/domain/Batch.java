package com.bmac.store.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Batch {
    private final UUID uuid;
    private final LocalDateTime dateTime;
    private final BatchStatus status;

    public Batch(UUID uuid, LocalDateTime dateTime, BatchStatus status) {
        this.dateTime = dateTime;
        this.uuid = uuid;
        this.status = status;
    }
}
