package com.bmac.store.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@EqualsAndHashCode
@AllArgsConstructor
public class Product {
    @Getter
    private final UUID uuid;

    @Getter
    private final String name;

    @Getter
    double price;

    public Product(String name, double price) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }


}
