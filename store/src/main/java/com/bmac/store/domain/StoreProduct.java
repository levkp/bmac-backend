package com.bmac.store.domain;

import java.util.Objects;
import java.util.UUID;

public class StoreProduct {
    private final UUID id;
    private final String name;
    private final double price;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public StoreProduct(UUID uuid, String name, double price) {
        this.id = uuid;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreProduct product = (StoreProduct) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
