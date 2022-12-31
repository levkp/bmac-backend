package com.bmac.store.ports.out.batch;

import com.bmac.store.domain.BatchActivity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BatchActivityLoadPort {
    List<BatchActivity> loadByBatchId(UUID id);
    List<BatchActivity> loadByOrderId(UUID id);
    List<UUID> loadActiveOrderIdsByBatchId(UUID id);
}
