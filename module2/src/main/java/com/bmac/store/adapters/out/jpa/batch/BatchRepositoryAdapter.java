package com.bmac.store.adapters.out.jpa.batch;

import com.bmac.store.domain.Batch;
import com.bmac.store.domain.BatchActivity;
import com.bmac.store.ports.out.batch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
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
    public Optional<Batch> loadByDateTime(LocalDate date) {
        Optional<BatchJpaEntity> optional = repository.findByDate(date);
        if (optional.isEmpty()) return Optional.empty();

        BatchJpaEntity jpaBatch = optional.get();
        Batch batch = new Batch();
        batch.setId(jpaBatch.getId());
        batch.setDate(jpaBatch.getDate());
        batch.setCreateTime(jpaBatch.getCreateTime());

        if (jpaBatch.getForwardTime() != null) {
            batch.setForwardTime(jpaBatch.getForwardTime());
        }

        activityLoader.loadByBatchId(batch.getId()).forEach(batch::addActivity);
        return Optional.of(batch);
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

    //    @Override
//    public Optional<Batch> loadByDateTime(LocalDate date) {
//        Optional<BatchJpaEntity> optional = repository.findByDate(date);
//        if (optional.isPresent()) {
//            BatchJpaEntity jpaEntity = optional.get();
//            return Optional.of(new Batch(jpaEntity.getId(), jpaEntity.getDate()));
//        }
//        return Optional.empty();
//    }
//    @Override
//    public Batch create(LocalDate date) {
//        BatchJpaEntity jpaEntity = new BatchJpaEntity(UUID.randomUUID(), date);
//        repository.save(jpaEntity);
//        createBatchActivity(jpaEntity.getUuid(), BatchActivityJpaEntity.BatchAction.CREATE);
//        return new Batch(jpaEntity.getUuid(), jpaEntity.getDate());
//    }
//
//    @Override
//    public Optional<Batch> loadByUuid(UUID uuid) {
//        Optional<BatchJpaEntity> optional = repository.findByUuid(uuid);
//        if (optional.isPresent()) {
//            BatchJpaEntity jpaEntity = optional.get();
//            return Optional.of(new Batch(jpaEntity.getUuid(), jpaEntity.getDate()));
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public void createBatchActivity(UUID batchUUID, BatchActivityJpaEntity.BatchAction action) {
//        BatchActivityJpaEntity activity = new BatchActivityJpaEntity();
//        activity.setBatch(batchUUID);
//        activity.setActivity(action);
//        activityRepository.save(activity);
//    }
}
