package com.bmac.factory.ports.out;

import com.bmac.factory.domain.FactoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadFactoryEntityPort<T extends FactoryEntity> {
    List<T> loadAll();
    Optional<T> loadById(UUID id);
}
