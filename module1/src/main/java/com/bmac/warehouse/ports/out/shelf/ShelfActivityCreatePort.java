package com.bmac.warehouse.ports.out.shelf;

import com.bmac.warehouse.domain.ShelfActivity;

import java.util.UUID;

public interface ShelfActivityCreatePort {
    void create(ShelfActivity activity);
}
