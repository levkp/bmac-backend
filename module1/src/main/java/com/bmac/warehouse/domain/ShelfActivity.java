package com.bmac.warehouse.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record ShelfActivity(ShelfActivity.Action action, UUID id, String shelfId, UUID itemId, double amount, LocalDateTime timestamp) {
    public enum Action {
        ADD,
        REMOVE
    }
}
