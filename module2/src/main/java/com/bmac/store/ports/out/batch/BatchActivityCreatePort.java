package com.bmac.store.ports.out.batch;

import com.bmac.store.adapters.out.db.batch.BatchActivityJpaEntity;
import com.bmac.store.domain.BatchActivity;

import java.util.UUID;

public interface BatchActivityCreatePort {
    void create(UUID batchId, BatchActivity activity);
}
