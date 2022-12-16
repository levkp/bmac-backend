package com.bmac.store.adapters.out.jpa.batch;

import com.bmac.store.domain.BatchActivity;
import com.bmac.store.ports.out.BatchActivityCreatePort;
import com.bmac.store.ports.out.BatchActivityLoadPort;
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
        BatchActivityJpaEntity jpaActivity = new BatchActivityJpaEntity();
        jpaActivity.setBatchId(batchId);
        jpaActivity.setOrderId(activity.orderId());
        jpaActivity.setAction(activity.action());
        jpaActivity.setTimestamp(activity.timestamp());
        repository.save(jpaActivity);
    }

    @Override
    public List<BatchActivity> loadByBatchId(UUID batchId) {
        return handleQueryResult(repository.findByBatchId(batchId));
    }

    @Override
    public List<BatchActivity> loadByOrderId(UUID id) {
        return handleQueryResult(repository.findByOrderId(id));
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

    private List<BatchActivity> handleQueryResult(List<BatchActivityJpaEntity> jpaEntities) {
        return jpaEntities.stream()
                .map(jpaEntity -> new BatchActivity(
                        jpaEntity.getAction(),
                        jpaEntity.getOrderId(),
                        jpaEntity.getTimestamp()))
                .toList();
    }
}
