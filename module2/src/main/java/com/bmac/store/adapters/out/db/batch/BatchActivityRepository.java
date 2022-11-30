package com.bmac.store.adapters.out.db.batch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BatchActivityRepository extends JpaRepository<BatchActivityJpaEntity, UUID> {  }
