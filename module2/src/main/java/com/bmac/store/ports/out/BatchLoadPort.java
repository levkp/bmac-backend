package com.bmac.store.ports.out;

import com.bmac.store.domain.Batch;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface BatchLoadPort {
    Optional<Batch> loadByDateTime(LocalDate date);
    Optional<Batch> loadById(UUID id);
}
