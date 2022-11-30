package com.bmac.store.adapters.out.db.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, UUID> {
    List<OrderJpaEntity> findAllByBatchUuid(UUID uuid);
}
