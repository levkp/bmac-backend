package com.bmac.store.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record BatchActivity(BatchActivity.Action action, UUID orderId, LocalDateTime timestamp) {
    public enum Action {
        RECEIVE,
        CANCEL,
    }
}
