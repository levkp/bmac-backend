package com.bmac.factory.ports.in;

import com.bmac.factory.domain.FactoryEntity;

import java.util.List;

public interface LoadAllFactoryEntityQuery<T extends FactoryEntity> {
    List<T> loadAll();
}
