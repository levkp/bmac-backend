package com.bmac.store.adapters.out.db;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Table(schema = "store", name="batches")
@NoArgsConstructor
@AllArgsConstructor
public class BatchEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    // Todo: unique = true
    @Column(unique = false, nullable = false)
    private LocalDate date;
}
