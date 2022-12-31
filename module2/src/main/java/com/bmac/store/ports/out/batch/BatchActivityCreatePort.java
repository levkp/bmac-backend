package com.bmac.store.ports.out.batch;

import com.bmac.store.domain.BatchActivity;

import java.util.UUID;

public interface BatchActivityCreatePort {
    void create(UUID batchId, BatchActivity activity);
}
