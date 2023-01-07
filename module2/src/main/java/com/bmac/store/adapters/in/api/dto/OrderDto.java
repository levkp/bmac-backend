package com.bmac.store.adapters.in.api.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@SuppressWarnings("unused")
public class OrderDto {
    @NotNull @NotEmpty
    private List<@Valid OrderItemDto> orderLine;

    public OrderDto() { }

    public List<OrderItemDto> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<OrderItemDto> orderLine) {
        this.orderLine = orderLine;
    }
}

