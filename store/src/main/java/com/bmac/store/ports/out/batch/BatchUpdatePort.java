package com.bmac.store.ports.out.batch;

import com.bmac.store.domain.Batch;

public interface BatchUpdatePort {
    void update(Batch batch);
}
