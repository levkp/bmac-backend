package com.bmac.store.adapters.out.db.batch;

import com.bmac.store.domain.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface BatchRepository extends JpaRepository<BatchEntity, UUID> {
    Optional<BatchEntity> findByDate(LocalDate date);
    Optional<BatchEntity> findByUuid(UUID uuid);

//    @Query("SELECT b.uuid FROM BatchEntity b WHERE b.date = ?1")
//    Optional<UUID> findUUIDByDate(LocalDate date);
}
