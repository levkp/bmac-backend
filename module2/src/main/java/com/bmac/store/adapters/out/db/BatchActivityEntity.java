package com.bmac.store.adapters.out.db;

import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "store", name = "batch_activities")
public class BatchActivityEntity {
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
    @Setter
    private UUID batch;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter
    private BatchAction activity;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public BatchActivityEntity() {
        this.timestamp = LocalDateTime.now();
    }
}
