package com.bmac.warehouse.adapters.out.jpa.order;

import com.bmac.warehouse.domain.Order;
import com.bmac.warehouse.ports.out.CreateOrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderJpaRepositoryAdapter implements CreateOrderPort {
    private final OrderRepository repository;

    @Autowired
    public OrderJpaRepositoryAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Order order) {
        repository.save(toJpaEntity(order));
    }

    private OrderJpaEntity toJpaEntity(Order order) {
        return new OrderJpaEntity(order.getId(), order.getBatchId(), order.getDate(), order.getReceived());
    }
}
