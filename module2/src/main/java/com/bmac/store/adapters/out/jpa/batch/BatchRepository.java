package com.bmac.store.adapters.out.jpa.batch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface BatchRepository extends JpaRepository<BatchJpaEntity, UUID> {
    Optional<BatchJpaEntity> findByDate(LocalDate date);
}
