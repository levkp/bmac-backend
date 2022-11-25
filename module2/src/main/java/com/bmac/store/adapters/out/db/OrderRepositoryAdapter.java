package com.bmac.store.adapters.out.db;

import com.bmac.store.domain.Order;
import com.bmac.store.ports.out.OrderReceivePort;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderReceivePort {

    private final OrderRepository repository;

    public OrderRepositoryAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Order o) {

    }
}
