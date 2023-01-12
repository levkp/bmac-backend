package com.bmac.warehouse.ports.in.stock;

import java.util.UUID;

public interface CountStockQuery {
    double countByShelfIdAndItemId(String shelfId, UUID itemId);
    double countByItemId(UUID itemId);
}
