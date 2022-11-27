package com.bmac.store.adapters.out.db;

import com.bmac.store.adapters.out.db.BatchActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BatchActivityRepository extends JpaRepository<BatchActivityEntity, UUID> {  }
