package com.bmac.warehouse.adapters.out.jpa.order;

import org.hibernate.annotations.MapKeyType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "wh.orders")
public class InboundOrderJpaEntity {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    // Todo: this should be unique, right
    @Type(type = "uuid-char")
    @Column(nullable = false)
    private UUID batchId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDateTime received;

    @ElementCollection
    @CollectionTable(name = "wh_orderitems", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    @Column(nullable = false, name = "amount")
    @MapKeyColumn(name = "item_id")
    @MapKeyType(@Type(type = "uuid-char") )
    private Map<UUID, Double> neededProducts;

    public InboundOrderJpaEntity(UUID id, UUID batchId, LocalDate date, LocalDateTime received, Map<UUID, Double> neededProducts) {
        this.id = id;
        this.batchId = batchId;
        this.date = date;
        this.received = received;
        this.neededProducts = neededProducts;
    }

    protected InboundOrderJpaEntity() { }
}
