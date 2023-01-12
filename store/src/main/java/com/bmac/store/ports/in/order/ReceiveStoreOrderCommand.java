package com.bmac.store.ports.in.order;

import java.util.Map;
import java.util.UUID;

public record ReceiveStoreOrderCommand(Map<UUID, Integer> orderLine) { }
