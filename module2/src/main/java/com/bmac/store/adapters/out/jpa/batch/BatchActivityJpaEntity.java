package com.bmac.store.adapters.out.jpa.batch;

import com.bmac.store.domain.BatchActivity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "store", name = "batch_activities")
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

    public void setBatchId(UUID batchId) {
        this.batchId = batchId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public void setAction(BatchActivity.Action action) {
        this.action = action;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
