package com.bmac.store.adapters.out.db;

import com.bmac.store.domain.BatchStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(schema = "store", name="batch")
@NoArgsConstructor
@AllArgsConstructor
public class BatchEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    // Todo: unique = true
    @Column(unique = false, nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BatchStatus status;
}
