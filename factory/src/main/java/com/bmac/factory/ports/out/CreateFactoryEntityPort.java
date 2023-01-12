package com.bmac.factory.ports.out;

import com.bmac.factory.domain.FactoryEntity;

public interface CreateFactoryEntityPort<T extends FactoryEntity> {
    void create(T t);
}
