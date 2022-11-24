package be.kdg.prog6.thebakery.store.domain;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public class Product {
    private final UUID id;
    private String name;
    double price;

    public Product() {
        id = UUID.randomUUID();
    }
}
