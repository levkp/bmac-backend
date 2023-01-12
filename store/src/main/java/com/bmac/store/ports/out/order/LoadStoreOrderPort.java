package com.bmac.store.ports.out.order;

import com.bmac.store.domain.StoreOrder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadStoreOrderPort {
    List<StoreOrder> loadAllByBatchId(UUID batchId);
    Optional<StoreOrder> loadById(UUID id);
    List<StoreOrder> loadAllByIds(List<UUID> ids);
}
