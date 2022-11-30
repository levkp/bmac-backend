package com.bmac.store.adapters.out.db.order;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_activities", schema = "store")
public class OrderActivityJpaEntity {

    public enum OrderAction {
        CREATE,
        CANCEL,
        FORWARD
    }

    public OrderActivityJpaEntity() {
        timestamp = LocalDateTime.now();
    }

    @Id
    @Type(type = "uuid-char")
    @Column(name = "uuid")
    @GeneratedValue(generator = "uuid")
    private UUID uuid;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderAction action;

    @Column(name = "order_id")
    @Type(type = "uuid-char")
    private UUID order;

    private LocalDateTime timestamp;

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setAction(OrderAction action) {
        this.action = action;
    }

    public void setOrder(UUID order) {
        this.order = order;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
