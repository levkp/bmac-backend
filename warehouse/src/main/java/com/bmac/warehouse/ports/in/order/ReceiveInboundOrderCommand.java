package com.bmac.warehouse.ports.in.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record ReceiveInboundOrderCommand(UUID batchId, LocalDate batchDate, LocalDateTime received, Map<UUID, Integer> products) {

}

