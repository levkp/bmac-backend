package com.bmac.store.adapters.out.jpa.batch;

import com.bmac.store.domain.Batch;
import com.bmac.store.ports.out.BatchActivityLoadPort;
import com.bmac.store.ports.out.BatchCreatePort;
import com.bmac.store.ports.out.BatchLoadPort;
import com.bmac.store.ports.out.BatchUpdatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BatchRepositoryAdapter implements BatchCreatePort, BatchLoadPort, BatchUpdatePort {

    private final BatchRepository repository;
    private final BatchActivityLoadPort activityLoader;

    @Autowired
    public BatchRepositoryAdapter(BatchRepository repository, BatchActivityLoadPort activityLoader) {
        this.repository = repository;
        this.activityLoader = activityLoader;
    }

    @Override
    public void create(Batch batch) {
        update(batch);
    }

    @Override
    public Optional<Batch> loadByDate(LocalDate date) {
        return handleQueryResult(repository.findByDate(date));
    }

    @Override
    public Optional<Batch> loadById(UUID id) {
        return handleQueryResult(repository.findById(id));
    }

    @Override
    public void update(Batch batch) {
        // Todo: are two constructors necessary?
        if (batch.getForwardTime() == null) {
            repository.save(new BatchJpaEntity(batch.getId(), batch.getDate(), batch.getCreateTime()));
        } else {
            repository.save(new BatchJpaEntity(batch.getId(), batch.getDate(), batch.getCreateTime(), batch.getForwardTime()));
        }
    }


    private Optional<Batch> handleQueryResult(Optional<BatchJpaEntity> optional) {
        if (optional.isEmpty()) return Optional.empty();
        BatchJpaEntity jpaEntity = optional.get();

        Batch batch = new Batch();
        batch.setId(jpaEntity.getId());
        batch.setDate(jpaEntity.getDate());
        batch.setCreateTime(jpaEntity.getCreateTime());
        batch.setActivityWindow(new Batch.ActivityWindow());

        if (jpaEntity.getForwardTime() != null) {
            batch.setForwardTime(jpaEntity.getForwardTime());
        }

        activityLoader.loadByBatchId(batch.getId()).forEach(batch::addActivity);

        return Optional.of(batch);
    }
}
