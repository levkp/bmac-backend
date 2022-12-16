package com.bmac.warehouse.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record ShelfActivity(ShelfActivity.Action action, UUID stockId, LocalDateTime timestamp) {
    public enum Action {
        LOAD,
        UNLOAD
    }
}
