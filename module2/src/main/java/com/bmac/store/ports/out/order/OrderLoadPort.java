package com.bmac.store.ports.out.order;

import com.bmac.store.adapters.out.db.order.OrderEntity;
import com.bmac.store.domain.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderLoadPort {
    List<OrderEntity> loadAllByBatchUuid(UUID uuid);
}
