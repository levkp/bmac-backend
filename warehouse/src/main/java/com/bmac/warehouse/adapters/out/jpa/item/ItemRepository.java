package com.bmac.warehouse.adapters.out.jpa.item;

import com.bmac.common.IngredientTemperature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemJpaEntity, UUID> {
    List<ItemJpaEntity> findAllByTemperature(IngredientTemperature temperature);
}
