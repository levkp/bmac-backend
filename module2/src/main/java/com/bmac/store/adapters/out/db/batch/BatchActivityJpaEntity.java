package com.bmac.store.adapters.out.db.batch;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "store", name = "batch_activities")
public class BatchActivityJpaEntity {
    public enum BatchAction {
        CREATE,
        FORWARD
    }

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    private UUID uuid;

    @Type(type = "uuid-char")
    @Column(nullable = false)
    private UUID batch;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BatchAction activity;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public BatchActivityJpaEntity() {
        this.timestamp = LocalDateTime.now();
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setBatch(UUID batch) {
        this.batch = batch;
    }

    public void setActivity(BatchAction activity) {
        this.activity = activity;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
