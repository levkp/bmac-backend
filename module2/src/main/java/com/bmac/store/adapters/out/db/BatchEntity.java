package com.bmac.store.adapters.out.db;

import com.bmac.store.domain.BatchStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(schema = "store", name="batch")
@NoArgsConstructor
public class BatchEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    @Column(unique = true, nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BatchStatus status;
}
