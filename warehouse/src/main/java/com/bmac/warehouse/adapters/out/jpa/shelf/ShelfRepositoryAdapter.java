package com.bmac.warehouse.adapters.out.jpa.shelf;

import com.bmac.warehouse.domain.Shelf;
import com.bmac.warehouse.domain.ShelfActivity;
import com.bmac.warehouse.ports.out.shelf.ShelfActivityLoadPort;
import com.bmac.warehouse.ports.out.shelf.ShelfLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ShelfRepositoryAdapter implements ShelfLoadPort {
    private final ShelfRepository repository;

    private final ShelfActivityLoadPort activityLoader;

    @Autowired
    public ShelfRepositoryAdapter(ShelfRepository repository, ShelfActivityLoadPort activityLoader) {
        this.repository = repository;
        this.activityLoader = activityLoader;
    }

    @Override
    public Optional<Shelf> loadById(String id) {
        return repository.findById(id).map(jpaEntity -> mapJpaEntity(jpaEntity, activityLoader.loadByShelfId(id)));
    }

    private Shelf mapJpaEntity(ShelfJpaEntity jpaEntity, List<ShelfActivity> activities) {
        Shelf shelf = new Shelf(jpaEntity.getId());
        activities.forEach(activity -> shelf.getActivityWindow().add(activity));
        return shelf;
    }
}
