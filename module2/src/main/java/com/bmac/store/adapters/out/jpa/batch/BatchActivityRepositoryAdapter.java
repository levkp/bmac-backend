package com.bmac.store.adapters.out.jpa.batch;

import com.bmac.store.domain.BatchActivity;
import com.bmac.store.ports.out.BatchActivityCreatePort;
import com.bmac.store.ports.out.BatchActivityLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return repository.findByBatchId(batchId)
                .stream()
                .map(jpaActivity -> new BatchActivity(
                        jpaActivity.getAction(),
                        jpaActivity.getOrderId(),
                        jpaActivity.getTimestamp()))
                .toList();
    }
}
