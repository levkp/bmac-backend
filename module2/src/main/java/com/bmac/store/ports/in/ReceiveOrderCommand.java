package com.bmac.store.ports.in;

import java.util.Map;
import java.util.UUID;

public record ReceiveOrderCommand(Map<UUID, Integer> orderLine) { }
