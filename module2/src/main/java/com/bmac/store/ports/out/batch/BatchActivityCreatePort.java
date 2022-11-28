package com.bmac.store.ports.out.batch;

import com.bmac.store.adapters.out.db.batch.BatchActivityEntity;

import java.util.UUID;

public interface BatchActivityCreatePort {
    void createBatchActivity(UUID batchUUID, BatchActivityEntity.BatchAction activity);
}
