package com.bmac.store.adapters.out.jpa.batch;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sto_batches")
public class BatchJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(unique = true, nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDateTime createTime;

    private LocalDateTime forwardTime;

    public BatchJpaEntity(UUID id, LocalDate date, LocalDateTime createTime, LocalDateTime forwardTime) {
        this.id = id;
        this.date = date;
        this.createTime = createTime;
        this.forwardTime = forwardTime;
    }

    public BatchJpaEntity(UUID id, LocalDate date, LocalDateTime createTime) {
        this.id = id;
        this.date = date;
        this.createTime = createTime;
    }

    protected BatchJpaEntity() {}

    public UUID getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getForwardTime() {
        return forwardTime;
    }
}
