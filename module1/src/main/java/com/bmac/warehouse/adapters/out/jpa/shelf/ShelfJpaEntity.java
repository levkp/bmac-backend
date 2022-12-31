package com.bmac.warehouse.adapters.out.jpa.shelf;

import javax.persistence.*;

@Entity
@Table(name = "wh.shelves")
public class ShelfJpaEntity {

    @Id
    private String id;

    private String sectorId;

    public ShelfJpaEntity(String id, String sectorId) {
        this.id = id;
        this.sectorId = sectorId;
    }

    protected ShelfJpaEntity() { }
}
