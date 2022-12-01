//package com.bmac.store.adapters.out.db.batch;
//
//import com.bmac.store.domain.Batch;
//import com.bmac.store.ports.out.batch.BatchActivityCreatePort;
//import com.bmac.store.ports.out.batch.BatchCreatePort;
//import com.bmac.store.ports.out.batch.BatchLoadPort;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public class BatchRepositoryAdapter
//        implements BatchLoadPort, BatchCreatePort, BatchActivityCreatePort {
//
//    private final BatchRepository repository;
//    private final BatchActivityRepository activityRepository;
//
//    @Autowired
//    public BatchRepositoryAdapter(BatchRepository repository, BatchActivityRepository activityRepository) {
//        this.repository = repository;
//        this.activityRepository = activityRepository;
//    }
//
//    @Override
//    public Optional<Batch> loadByDateTime(LocalDate date) {
//        Optional<BatchJpaEntity> optional = repository.findByDate(date);
//        if (optional.isPresent()) {
//            BatchJpaEntity jpaEntity = optional.get();
//            return Optional.of(new Batch(jpaEntity.getUuid(), jpaEntity.getDate()));
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
//}
