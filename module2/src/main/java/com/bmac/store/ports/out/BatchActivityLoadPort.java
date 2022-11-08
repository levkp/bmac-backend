package com.bmac.store.ports.out;

import com.bmac.store.domain.BatchActivity;

import java.util.List;
import java.util.UUID;

public interface BatchActivityLoadPort {
    List<BatchActivity> loadByBatchId(UUID batchId);
}
