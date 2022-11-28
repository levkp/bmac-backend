package com.bmac.store.adapters.out.db.order;

import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_activities", schema = "store")
public class OrderActivityEntity {

    public enum OrderAction {
        CREATE,
        CANCEL,
        FORWARD
    }

    public OrderActivityEntity() {
        timestamp = LocalDateTime.now();
    }

    @Id
    @Type(type = "uuid-char")
    @Column(name = "uuid")
    @GeneratedValue(generator = "uuid")
    private UUID uuid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private OrderAction action;

    @Column(name = "order_id")
    @Setter
    @Type(type = "uuid-char")
    private UUID order;

    private LocalDateTime timestamp;
}
