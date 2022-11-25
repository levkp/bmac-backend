package com.bmac.store.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode
public class Product {
    @Getter
    private final UUID uuid;

    @Getter
    private String name;

    @Getter
    double price;

    public Product() {
        uuid = UUID.randomUUID();
    }
}
