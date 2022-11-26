package com.bmac.store.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface BatchRepository extends JpaRepository<BatchEntity, UUID> {
    Optional<BatchEntity> findByDate(LocalDate date);
}
