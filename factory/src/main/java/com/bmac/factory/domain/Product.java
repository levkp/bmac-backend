package com.bmac.factory.domain;

import java.util.UUID;

public class Product extends FactoryEntity {
    private String name;

    public Product(UUID id, String name) {
        super(id);
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
