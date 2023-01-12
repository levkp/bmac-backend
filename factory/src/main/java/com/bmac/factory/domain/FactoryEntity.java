package com.bmac.factory.domain;

import java.util.UUID;

public abstract class FactoryEntity {

    protected UUID id;

    public FactoryEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
