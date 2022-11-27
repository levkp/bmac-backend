package com.bmac.store.adapters.out.db;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.store.domain.Order;
import com.bmac.store.ports.out.OrderActivityCreatePort;
import com.bmac.store.ports.out.OrderReceivePort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public class OrderRepositoryAdapter implements OrderReceivePort, OrderActivityCreatePort {

    private final OrderRepository orderRepository;

    private final OrderActivityRepository orderActivityRepository;

    public OrderRepositoryAdapter(OrderRepository repository, OrderActivityRepository activityRepository) {
        this.orderRepository = repository;
        this.orderActivityRepository = activityRepository;
    }

    @Override
    public void receive(Order order) {
        OrderEntity jpaEntity = new OrderEntity();
        jpaEntity.setUuid(order.getUuid());
        jpaEntity.setTimestamp(order.getTimestamp());
        jpaEntity.setBatchUuid(order.getBatch().getUuid());
        jpaEntity.setProductUuid(order.getProduct().getUuid());
        jpaEntity.setAmount(order.getAmount());

        orderRepository.save(jpaEntity);
        createActivity(order.getUuid(), OrderActivityEntity.OrderAction.CREATE);
    }

    @Override
    public void createActivity(UUID orderUuid, OrderActivityEntity.OrderAction action) {
        OrderActivityEntity activity = new OrderActivityEntity();
        activity.setOrder(orderUuid);
        activity.setAction(action);
        orderActivityRepository.save(activity);
    }
}
