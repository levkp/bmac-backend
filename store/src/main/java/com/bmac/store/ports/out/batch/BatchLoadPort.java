package com.bmac.store.ports.out.batch;

import com.bmac.store.domain.Batch;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface BatchLoadPort {
    Optional<Batch> loadByDate(LocalDate date);
    Optional<Batch> loadById(UUID id);
}
