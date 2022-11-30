package com.bmac.store.ports.out.order;

import com.bmac.store.adapters.out.db.order.OrderActivityJpaEntity;

import java.util.UUID;

public interface OrderActivityCreatePort {
    void createActivity(UUID orderUuid, OrderActivityJpaEntity.OrderAction action);
}
