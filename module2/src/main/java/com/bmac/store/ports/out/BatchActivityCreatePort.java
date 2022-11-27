package com.bmac.store.ports.out;

import com.bmac.store.adapters.out.db.BatchActivityEntity;

import java.util.UUID;

public interface BatchActivityCreatePort {
    void createBatchActivity(UUID batchUUID, BatchActivityEntity.BatchAction activity);
}
