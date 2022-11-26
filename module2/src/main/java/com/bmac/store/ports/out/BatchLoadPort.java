package com.bmac.store.ports.out;

import com.bmac.store.domain.Batch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface BatchLoadPort {
    Optional<Batch> loadByDateTime(LocalDate date);
}
