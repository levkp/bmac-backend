package com.bmac.store.adapters.out.jpa.batch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BatchActivityRepository extends JpaRepository<BatchActivityJpaEntity, UUID> {
    List<BatchActivityJpaEntity> findByBatchId(UUID id);
}
