package com.bmac.warehouse.adapters.out.jpa.sector;

import com.bmac.warehouse.adapters.out.jpa.shelf.ShelfJpaEntity;
import com.bmac.warehouse.domain.Sector;
import com.bmac.warehouse.ports.out.sector.SectorCreatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SectorRepositoryAdapter implements SectorCreatePort {

    private final SectorRepository repository;

    @Autowired
    public SectorRepositoryAdapter(SectorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Sector sector) {
        repository.save(new SectorJpaEntity(sector.getId(), sector.getTemperature(), sector.getShelves()
                .stream()
                .map(shelf -> new ShelfJpaEntity(shelf.getId(), sector.getId()))
                .toList()));
    }
}
