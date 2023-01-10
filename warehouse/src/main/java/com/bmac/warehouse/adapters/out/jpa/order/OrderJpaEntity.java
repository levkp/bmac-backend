package com.bmac.warehouse.adapters.out.jpa.order;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "wh.orders")
public class OrderJpaEntity {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Type(type = "uuid-char")
    @Column(nullable = false)
    private UUID batchId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDateTime received;

    public OrderJpaEntity(UUID id, UUID batchId, LocalDate date, LocalDateTime received) {
        this.id = id;
        this.batchId = batchId;
        this.date = date;
        this.received = received;
    }

    protected OrderJpaEntity() { }
}
