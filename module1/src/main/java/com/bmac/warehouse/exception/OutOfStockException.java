package com.bmac.warehouse.exception;

import java.util.UUID;

public class OutOfStockException extends Exception {
    public OutOfStockException(String shelfId, UUID itemId) {
        super(String.format("Item with id %s is out of stock on shelf %s", itemId.toString(), shelfId));
    }
}
