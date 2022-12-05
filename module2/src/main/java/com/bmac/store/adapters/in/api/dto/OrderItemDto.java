package com.bmac.store.adapters.in.api.dto;

import java.util.UUID;

@SuppressWarnings("unused")
public class OrderItemDto {
    private UUID productId;
    private int amount;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
