package com.bmac.store.adapters.out.db.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderActivityRepository extends JpaRepository<OrderActivityJpaEntity, UUID> {


}
