package com.bmac.warehouse.adapters.out.jpa.item;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.exception.EntityConstraintException;
import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.ports.out.item.ItemCreatePort;
import com.bmac.warehouse.ports.out.item.LoadItemPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ItemRepositoryAdapter implements ItemCreatePort, LoadItemPort {

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
            throw new EntityConstraintException(exception.getMessage());
        }
    }

    @Override
    public List<Item> loadAll() {
        return repository.findAll().stream()
                .map(this::fromJpaEntity)
                .toList();
    }

    @Override
    public Optional<Item> loadById(UUID id) {
        return repository.findById(id).map(this::fromJpaEntity);
    }

    @Override
    public List<Item> loadAllByTemperature(IngredientTemperature temperature) {
        return repository.findAllByTemperature(temperature).stream().map(this::fromJpaEntity).toList();
    }

    private Item fromJpaEntity(ItemJpaEntity jpaEntity) {
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
