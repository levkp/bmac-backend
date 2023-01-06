package com.bmac.warehouse.adapters.out.jpa.item;

import com.bmac.warehouse.exception.WarehouseEntityConstraintException;
import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.ports.out.item.ItemCreatePort;
import com.bmac.warehouse.ports.out.item.ItemLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ItemRepositoryAdapter implements ItemCreatePort, ItemLoadPort {

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
                .map(this::mapJpaEntity)
                .toList();
    }

    @Override
    public Optional<Item> loadById(UUID id) {
        return repository.findById(id).map(this::mapJpaEntity);
    }

    private Item mapJpaEntity(ItemJpaEntity jpaEntity) {
        return new Item(
                jpaEntity.getId(),
                jpaEntity.getName(),
                jpaEntity.getMinimum(),
                jpaEntity.getMaximum(),
                jpaEntity.getTemperature(),
                jpaEntity.getUnit(),
                jpaEntity.getExpiryDays());
    }

}
