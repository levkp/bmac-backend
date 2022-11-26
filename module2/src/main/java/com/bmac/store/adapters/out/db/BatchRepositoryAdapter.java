package com.bmac.store.adapters.out.db;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.store.domain.Batch;
import com.bmac.store.domain.BatchStatus;
import com.bmac.store.ports.out.BatchCreatePort;
import com.bmac.store.ports.out.BatchLoadPort;
import com.bmac.store.ports.out.BatchUpdatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BatchRepositoryAdapter implements BatchLoadPort, BatchUpdatePort, BatchCreatePort {

    private final BatchRepository repository;

    @Autowired
    public BatchRepositoryAdapter(BatchRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Batch> loadByDateTime(LocalDate date) {
        Optional<BatchEntity> optional = repository.findByDate(date);
        if (optional.isPresent()) {
            BatchEntity jpaEntity = optional.get();
            return Optional.of(new Batch(jpaEntity.getUuid(), jpaEntity.getDate(), jpaEntity.getStatus()));
        }
        return Optional.empty();
    }

    // Todo: check if batch already exists
    @Override
    public Batch create() {
        LocalDate date = LocalDate.now();
        if (DailyCutoffTime.hasPassed()) {
            date = LocalDate.now().plusDays(1);
        }
        BatchEntity jpaEntity = new BatchEntity(UUID.randomUUID(), date, BatchStatus.ACTIVE);
        repository.save(jpaEntity);
        return new Batch(jpaEntity.getUuid(), jpaEntity.getDate(), jpaEntity.getStatus());
    }
}
