package com.bmac.warehouse.adapters.out.jpa.sector;

import com.bmac.warehouse.adapters.out.jpa.shelf.ShelfJpaEntity;
import com.bmac.warehouse.domain.Temperature;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wh.sectors")
public class SectorJpaEntity {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private Temperature temperature;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sectorId")
    private List<ShelfJpaEntity> shelves = new ArrayList<>();

    public SectorJpaEntity(String id, Temperature temperature, List<ShelfJpaEntity> shelves) {
        this.id = id;
        this.temperature = temperature;
        this.shelves = shelves;
    }

    protected SectorJpaEntity() { }

}
