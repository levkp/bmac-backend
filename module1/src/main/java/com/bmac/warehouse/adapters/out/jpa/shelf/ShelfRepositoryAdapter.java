package com.bmac.warehouse.adapters.out.jpa.shelf;

import com.bmac.warehouse.domain.Shelf;
import com.bmac.warehouse.ports.out.shelf.ShelfLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ShelfRepositoryAdapter implements ShelfLoadPort {
    private final ShelfRepository repository;

    @Autowired
    public ShelfRepositoryAdapter(ShelfRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Shelf> load(String id) {
        return null;
    }
}
