package com.bmac.warehouse.ports.out.shelf;

import com.bmac.warehouse.domain.Shelf;

import java.util.Optional;

public interface ShelfLoadPort {
    Optional<Shelf> load(String id);
}
