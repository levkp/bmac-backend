package com.bmac.store.adapters.out.db;

import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "store")
public class OrderEntity {

    @Id
    @Type(type = "uuid-char")
    @Getter
    private UUID uuid;

    @Column(nullable = false)
    @Getter
    private LocalDateTime timestamp;

//    @OneToMany
//    private List<OrderLineItemJpaEntity> orderLine;

    public OrderEntity() {
//        orderLine = new ArrayList<>();
    }
}
