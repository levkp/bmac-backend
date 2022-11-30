package com.bmac.store.ports.out.batch;

import com.bmac.store.adapters.out.db.batch.BatchActivityJpaEntity;

import java.util.UUID;

public interface BatchActivityCreatePort {
    void createBatchActivity(UUID batchUUID, BatchActivityJpaEntity.BatchAction activity);
}
