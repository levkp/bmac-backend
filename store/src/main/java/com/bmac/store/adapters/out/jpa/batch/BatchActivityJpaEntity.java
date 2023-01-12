package com.bmac.store.adapters.out.jpa.batch;

import com.bmac.store.domain.BatchActivity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sto_batchactivities")
public class BatchActivityJpaEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Type(type = "uuid-char")
    @Column(nullable = false)
    private UUID batchId;

    @Type(type = "uuid-char")
    @Column(nullable = false)
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BatchActivity.Action action;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public BatchActivityJpaEntity(UUID batchId, UUID orderId, BatchActivity.Action action, LocalDateTime timestamp) {
        this.batchId = batchId;
        this.orderId = orderId;
        this.action = action;
        this.timestamp = timestamp;
    }

    protected BatchActivityJpaEntity() { }

    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public BatchActivity.Action getAction() {
        return action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public UUID getBatchId() {
        return batchId;
    }
}
