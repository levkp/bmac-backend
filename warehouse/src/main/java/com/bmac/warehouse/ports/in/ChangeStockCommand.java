package com.bmac.warehouse.ports.in;

import java.util.UUID;

public record ChangeStockCommand(String shelfId, UUID itemId, double amount) { }
