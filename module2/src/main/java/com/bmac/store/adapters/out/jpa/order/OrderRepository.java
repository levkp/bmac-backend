package com.bmac.store.adapters.out.jpa.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, UUID> {
}
