package com.bmac.warehouse.ports.in.stock;

import java.util.UUID;

public record LoadStockCommand(UUID shelfId, UUID itemId, double amount) { }
