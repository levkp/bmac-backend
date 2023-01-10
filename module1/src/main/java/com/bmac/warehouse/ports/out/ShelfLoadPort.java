package com.bmac.warehouse.ports.out;

import com.bmac.warehouse.domain.Shelf;

import java.util.Optional;

public interface ShelfLoadPort {
    Optional<Shelf> loadById(String id);
}
