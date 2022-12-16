package com.bmac.store.adapters.in.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.UUID;

@SuppressWarnings("unused")
public class OrderItemDto {
    @NotNull
    private UUID productId;
    @Min(1) @Max(500)
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
