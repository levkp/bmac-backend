package com.bmac.store.core;

import com.bmac.store.core.exception.StoreEntityNotFoundException;
import com.bmac.store.domain.Batch;
import com.bmac.store.ports.out.batch.BatchLoadPort;
import com.bmac.store.ports.out.batch.DailyBatchUuidQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultDailyBatchUuidQuery implements DailyBatchUuidQuery {
    private final BatchLoadPort batchLoader;

    @Override
    public UUID getDailyBatchUuid() {
        final LocalDate today = LocalDate.now();
        Optional<Batch> optional = batchLoader.loadByDateTime(today);
        if (optional.isEmpty()) throw new StoreEntityNotFoundException(Batch.class, LocalDate.class, today.toString());
        return optional.get().getUuid();
    }
}
