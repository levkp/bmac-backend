package com.bmac.store.adapters.out.db;

import com.bmac.store.domain.Order;
import com.bmac.store.ports.out.OrderCreatePort;

public class OrderRepositoryAdapter implements OrderCreatePort {

    private final OrderRepository repository;

    public OrderRepositoryAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Order o) {

    }
}
