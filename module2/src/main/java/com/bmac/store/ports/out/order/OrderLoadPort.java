package com.bmac.store.ports.out.order;

import com.bmac.store.adapters.out.db.order.OrderJpaEntity;

import java.util.List;
import java.util.UUID;

public interface OrderLoadPort {
    List<OrderJpaEntity> loadAllByBatchUuid(UUID uuid);
}
