package com.bmac.store.adapters.out.jpa.product;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "sto_products")
public class StoreProductJpaEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    protected StoreProductJpaEntity() {

    }

    public StoreProductJpaEntity(UUID uuid, String name, double price) {
        this.id = uuid;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
