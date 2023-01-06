package com.bmac.store.adapters.out.jpa.batch;

import com.bmac.store.domain.BatchActivity;
import com.bmac.store.ports.out.batch.BatchActivityCreatePort;
import com.bmac.store.ports.out.batch.BatchActivityLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class BatchActivityRepositoryAdapter implements BatchActivityCreatePort, BatchActivityLoadPort {

    private final BatchActivityRepository repository;

    @Autowired
    public BatchActivityRepositoryAdapter(BatchActivityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(UUID batchId, BatchActivity activity) {
        repository.save(toJpaEntity(activity, batchId));
    }

    @Override
    public void createAll(UUID batchId, List<BatchActivity> activities) {
        repository.saveAll(activities.stream()
                .map(activity -> toJpaEntity(activity, batchId))
                .toList()
        );
    }

    @Override
    public List<BatchActivity> loadByBatchId(UUID batchId) {
        return repository.findByBatchId(batchId).stream().map(this::fromJpaEntity).toList();
    }

    @Override
    public List<BatchActivity> loadByOrderId(UUID id) {
        return repository.findByOrderId(id).stream().map(this::fromJpaEntity).toList();
    }

    @Override
    public List<UUID> loadActiveOrderIdsByBatchId(UUID id) {
        /* An order is active, if it wasn't cancelled or forwarded. In other words, its ID must appear in the batch
         * activities only once. If the resulting array of this method is empty, then either all orders were cancelled,
         * or the batch was already forwarded.
         */
        List<UUID> orderIds = loadByBatchId(id).stream()
                .map(BatchActivity::orderId)
                .toList();
        return orderIds.stream()
                .filter(orderId -> Collections.frequency(orderIds, orderId) == 1)
                .toList();
    }

    private BatchActivityJpaEntity toJpaEntity(BatchActivity activity, UUID batchId) {
        return new BatchActivityJpaEntity(batchId, activity.orderId(), activity.action(), activity.timestamp());
    }

    private BatchActivity fromJpaEntity(BatchActivityJpaEntity jpaEntity) {
        return new BatchActivity(jpaEntity.getAction(), jpaEntity.getOrderId(), jpaEntity.getTimestamp());
    }
}
