package com.bmac.warehouse.ports.out.item;

import com.bmac.common.IngredientTemperature;
import com.bmac.warehouse.domain.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadItemPort {
    List<Item> loadAll();
    Optional<Item> loadById(UUID id);

    List<Item> loadAllByTemperature(IngredientTemperature temperature);

}
