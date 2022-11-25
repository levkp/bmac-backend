package com.bmac.store.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@EqualsAndHashCode
public class Product {
    @Getter
    private final UUID uuid;

    @Getter
    private final String name;

    @Getter
    double price;

    public Product(String name, double price) {
        uuid = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

}
