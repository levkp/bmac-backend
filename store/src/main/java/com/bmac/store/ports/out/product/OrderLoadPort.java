package com.bmac.store.ports.out.product;

import com.bmac.store.domain.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderLoadPort {
    List<Order> loadAllByBatchId(UUID batchId);
    Optional<Order> loadById(UUID id);
    List<Order> loadAllByIds(List<UUID> ids);
}
