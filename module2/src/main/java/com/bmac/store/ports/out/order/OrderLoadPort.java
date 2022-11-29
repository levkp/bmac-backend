package com.bmac.store.ports.out.order;

import com.bmac.store.adapters.out.db.order.OrderEntity;

import java.util.List;
import java.util.UUID;

public interface OrderLoadPort {
    List<OrderEntity> loadAllByBatchUuid(UUID uuid);
}
