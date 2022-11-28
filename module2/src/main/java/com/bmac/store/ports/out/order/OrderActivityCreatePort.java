package com.bmac.store.ports.out.order;

import com.bmac.store.adapters.out.db.order.OrderActivityEntity;

import java.util.UUID;

public interface OrderActivityCreatePort {
    void createActivity(UUID orderUuid, OrderActivityEntity.OrderAction action);
}
