package com.bmac.store.ports.out.product;

import com.bmac.store.domain.Order;

public interface OrderCreatePort {
    void create(Order order);
}
