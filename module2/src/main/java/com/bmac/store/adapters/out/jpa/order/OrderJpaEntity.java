package com.bmac.store.adapters.out.jpa.order;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "store")
public class OrderJpaEntity {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Type(type = "uuid-char")
    @Column(nullable = false)
    private UUID batchId;

    @Column(nullable = false)
    private LocalDateTime timestamp;

//    (targetClass = Integer.class)
    @ElementCollection
    @CollectionTable(name = "order_lines", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    @Column(nullable = false, name = "amount")
    @MapKeyColumn(name = "product_id")
    private Map<String, Integer> orderLine = new HashMap<>();

    public OrderJpaEntity() {

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }
}
