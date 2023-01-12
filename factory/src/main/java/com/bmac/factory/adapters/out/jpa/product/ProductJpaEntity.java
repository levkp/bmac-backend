package com.bmac.factory.adapters.out.jpa.product;


import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "fac_products")
public class ProductJpaEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    public ProductJpaEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    protected ProductJpaEntity() { }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
