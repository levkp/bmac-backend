package com.bmac.store.adapters.out.db;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "store")
public class OrderEntity {

    @Id
    @Setter
    @Type(type = "uuid-char")
    private UUID uuid;

    @Setter
    @Type(type = "uuid-char")
    private UUID batchUuid;

    @Setter
    @Type(type = "uuid-char")
    private UUID productUuid;

    @Setter
    private double amount;

    @Column(nullable = false)
    @Setter
    private LocalDateTime timestamp;


    public OrderEntity() {
//        orderLine = new ArrayList<>();
    }
}
