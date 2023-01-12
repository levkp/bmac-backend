package com.bmac.warehouse.adapters.out.jpa.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InboundOrderRepository extends JpaRepository<InboundOrderJpaEntity, UUID> { }
