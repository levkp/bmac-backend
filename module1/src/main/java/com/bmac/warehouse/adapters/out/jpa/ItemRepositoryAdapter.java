package com.bmac.warehouse.adapters.out.jpa;

import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.ports.out.CreateItemPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryAdapter implements CreateItemPort {

    private final ItemRepository repository;

    @Autowired
    public ItemRepositoryAdapter(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Item item) {
        ItemJpaEntity jpaEntity = new ItemJpaEntity(
                item.getId(),
                item.getName(),
                item.getMinimum(),
                item.getMaximum(),
                item.getTemperature(),
                item.getUnit());
        repository.save(jpaEntity);
    }
}
