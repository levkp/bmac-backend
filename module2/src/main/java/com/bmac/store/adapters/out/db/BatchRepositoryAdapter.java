package com.bmac.store.adapters.out.db;

import com.bmac.store.domain.Batch;
import com.bmac.store.ports.out.BatchLoadPort;
import com.bmac.store.ports.out.BatchUpdatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class BatchRepositoryAdapter implements BatchLoadPort, BatchUpdatePort {

    private final BatchRepository repository;

    @Autowired
    public BatchRepositoryAdapter(BatchRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Batch> loadByDateTime(LocalDateTime dateTime) {
        Optional<BatchEntity> optional = repository.findByDateTime(dateTime);
        if (optional.isPresent()) {
            BatchEntity jpaEntity = optional.get();
            return Optional.of(new Batch(jpaEntity.getUuid(), jpaEntity.getDateTime(), jpaEntity.getStatus()));
        }
        return Optional.empty();
    }
}
