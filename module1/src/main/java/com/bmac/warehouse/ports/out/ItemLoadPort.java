package com.bmac.warehouse.ports.out;

import com.bmac.warehouse.domain.Item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemLoadPort {
    List<Item> loadAll();
    Optional<Item> loadById(UUID id);
}
