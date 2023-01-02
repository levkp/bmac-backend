package com.bmac.warehouse.ports.out.shelf;

import com.bmac.warehouse.domain.ShelfActivity;

import java.util.List;

public interface ShelfActivityLoadPort {
    List<ShelfActivity> loadByShelfId(String id);
}
