package com.bmac.warehouse.adapters.out.jpa;

import com.bmac.warehouse.adapters.exception.WarehouseEntityConstraintException;
import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.ports.out.CreateItemPort;
import com.bmac.warehouse.ports.out.LoadItemPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryAdapter implements CreateItemPort, LoadItemPort {

    private final ItemRepository repository;

    @Autowired
    public ItemRepositoryAdapter(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Item item) {
        try {
            repository.save(new ItemJpaEntity(
                    item.getId(),
                    item.getName(),
                    item.getMinimum(),
                    item.getMaximum(),
                    item.getTemperature(),
                    item.getUnit()));
        } catch (DataIntegrityViolationException exception) {
            throw new WarehouseEntityConstraintException();
        }
    }

    @Override
    public List<Item> loadAll() {
        return repository.findAll().stream()
                .map(jpaEntity -> new Item(
                        jpaEntity.getId(),
                        jpaEntity.getName(),
                        jpaEntity.getMinimum(),
                        jpaEntity.getMaximum(),
                        jpaEntity.getTemperature(),
                        jpaEntity.getUnit())).toList();
    }
}
