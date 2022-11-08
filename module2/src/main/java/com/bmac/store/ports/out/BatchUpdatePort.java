package com.bmac.store.ports.out;

import com.bmac.store.domain.Batch;

public interface BatchUpdatePort {
    void update(Batch batch);
}
