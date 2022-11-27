package com.bmac.store.adapters.out.db;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "products")
@AllArgsConstructor
@Getter
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
}
