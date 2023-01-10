package com.bmac.store.exception;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderAlreadyCancelledException extends RuntimeException {

    public OrderAlreadyCancelledException(UUID id, LocalDateTime timestamp) {
        super("Order " + id.toString() + " was already cancelled on " + timestamp.toString());
    }
}
