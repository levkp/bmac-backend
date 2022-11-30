package com.bmac.store.adapters.out.db.product;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    protected ProductEntity() {

    }

    public ProductEntity(UUID uuid, String name, double price) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
