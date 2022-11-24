package com.bmac.store.adapters.out.db;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "order")
public class OrderJpaEntitiy {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public OrderJpaEntitiy() { }
}
