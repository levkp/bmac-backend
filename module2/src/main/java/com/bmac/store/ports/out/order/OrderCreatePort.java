package com.bmac.store.ports.out.order;

import com.bmac.store.domain.Order;

public interface OrderCreatePort {
    void create(Order order);
}