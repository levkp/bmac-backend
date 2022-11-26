package com.bmac.store.adapters.out.db;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "order_lines")
public class OrderLineItemEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    @OneToOne(optional = false)
    private ProductEntity product;

    @Column(nullable = false)
    private int amount;
}
