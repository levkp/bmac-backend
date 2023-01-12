package com.bmac.warehouse.adapters.out.jpa.shelf;

import com.bmac.warehouse.domain.ShelfActivity;
import com.bmac.warehouse.ports.out.shelf.ShelfActivityCreatePort;
import com.bmac.warehouse.ports.out.shelf.ShelfActivityLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ShelfActivityRepositoryAdapter implements ShelfActivityLoadPort, ShelfActivityCreatePort {

    private final ShelfActivityRepository repository;

    @Autowired
    public ShelfActivityRepositoryAdapter(ShelfActivityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ShelfActivity> loadByShelfId(String id) {
        return repository.findByShelfId(id).stream()
                .map(this::fromJpaEntity)
                .toList();
    }

    @Override
    public List<ShelfActivity> loadByItemId(UUID itemId) {
        return repository.findByItemId(itemId).stream()
                .map(this::fromJpaEntity)
                .toList();
    }

    @Override
    public List<ShelfActivity> loadByShelfIdAndItemId(String shelfId, UUID itemId) {
        return loadByShelfId(shelfId).stream()
                .filter(activity -> activity.itemId() == itemId)
                .toList();
    }

    @Override
    public void create(ShelfActivity activity) {
        repository.save(toJpaEntity(activity));
    }

    private ShelfActivity fromJpaEntity(ShelfActivityJpaEntity jpaEntity) {
        return new ShelfActivity(
                jpaEntity.getAction(),
                jpaEntity.getShelfId(),
                jpaEntity.getItemId(),
                jpaEntity.getAmount(),
                jpaEntity.getTimestamp()
        );
    }

    private ShelfActivityJpaEntity toJpaEntity(ShelfActivity activity) {
        return new ShelfActivityJpaEntity(
                activity.shelfId(),
                activity.itemId(),
                activity.action(),
                activity.amount(),
                activity.timestamp()
        );
    }
}
