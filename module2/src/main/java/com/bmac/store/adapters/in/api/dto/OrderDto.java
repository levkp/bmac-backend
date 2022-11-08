package com.bmac.store.adapters.in.api.dto;

import java.util.List;

@SuppressWarnings("unused")
public class OrderDto {

    private List<OrderItemDto> orderLine;

    public OrderDto() {}

    public List<OrderItemDto> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<OrderItemDto> orderLine) {
        this.orderLine = orderLine;
    }
}

