package com.bmac.factory.ports.out;

import com.bmac.factory.domain.FactoryEntity;

import java.util.List;

public interface LoadFactoryEntityPort<T extends FactoryEntity> {
    List<T> loadAll();
}
