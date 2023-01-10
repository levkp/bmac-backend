package com.bmac.warehouse.ports.out;

import com.bmac.warehouse.domain.Order;

public interface CreateOrderPort {
    void create(Order order);
}
