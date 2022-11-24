package com.bmac.store.adapters.out.db;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class CustomerJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column
    private LocalDate clientSince;


    public CustomerJpaEntity() { }
}
