package com.bmac.store.adapters.out.db.batch;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(schema = "store", name="batches")
public class BatchJpaEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    private UUID uuid;

    // Todo: unique = true
    @Column(unique = false, nullable = false)
    private LocalDate date;

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

    public BatchJpaEntity() {}
}
