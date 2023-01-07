package com.bmac.common.events;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record BatchForwardedEvent(UUID batchId, LocalDate batchDate, List<OrderLineItem> orderLine) {
    public record OrderLineItem(UUID id, LocalDateTime timestamp, Map<UUID, Integer> products) { }
}
