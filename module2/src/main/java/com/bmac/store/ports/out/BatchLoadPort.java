package com.bmac.store.ports.out;

import com.bmac.store.domain.Batch;

import java.util.Optional;

public interface BatchLoadPort {
    Optional<Batch> loadActiveBatch();
}
