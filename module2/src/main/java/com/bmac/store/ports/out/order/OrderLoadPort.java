package com.bmac.store.ports.out.order;

import com.bmac.store.domain.Order;

import java.util.List;
import java.util.UUID;

public interface OrderLoadPort {
    List<Order> loadAllByBatchId(UUID batchId);
}
