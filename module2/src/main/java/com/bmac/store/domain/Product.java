package com.bmac.store.domain;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private final UUID uuid;

    private final String name;

    double price;

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Product(UUID uuid, String name, double price) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(uuid, product.uuid) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, price);
    }
}
