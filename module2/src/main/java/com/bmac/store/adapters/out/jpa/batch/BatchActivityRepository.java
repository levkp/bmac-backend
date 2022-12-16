package com.bmac.store.adapters.out.jpa.batch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BatchActivityRepository extends JpaRepository<BatchActivityJpaEntity, UUID> {
    List<BatchActivityJpaEntity> findByBatchId(UUID id);

    List<BatchActivityJpaEntity> findByOrderId(UUID id);
}
