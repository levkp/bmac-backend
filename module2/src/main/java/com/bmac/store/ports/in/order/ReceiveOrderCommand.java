package com.bmac.store.ports.in.order;

import com.bmac.store.domain.Order;

import java.util.UUID;

public record ReceiveOrderCommand(Order order) { }
