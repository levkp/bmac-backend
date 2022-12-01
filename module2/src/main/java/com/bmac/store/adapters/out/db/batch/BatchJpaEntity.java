package com.bmac.store.adapters.out.db.batch;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import com.bmac.store.adapters.out.db.order.OrderJpaEntity;
import org.hibernate.annotations.Type;

@Entity
@Table(schema = "store", name = "batches")
public class BatchJpaEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    private UUID uuid;

    @Column(unique = true, nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "batchUuid", fetch = FetchType.LAZY)
    private final List<OrderJpaEntity> orders = new ArrayList<>();

    public UUID getUuid() {
        return uuid;
    }

    public LocalDate getDate() {
        return date;
    }

    public BatchJpaEntity(UUID uuid, LocalDate date) {
        this.uuid = uuid;
        this.date = date;
    }
    protected BatchJpaEntity() {}
}
