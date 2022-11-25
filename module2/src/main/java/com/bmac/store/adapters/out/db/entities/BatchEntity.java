package com.bmac.store.adapters.out.db.entities;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(schema = "store", name="batch_active")
@NoArgsConstructor
public class BatchEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

}
