package com.bmac.warehouse.ports.out.shelf;

import com.bmac.warehouse.domain.ShelfActivity;

import java.util.List;
import java.util.UUID;

public interface ShelfActivityLoadPort {
    List<ShelfActivity> loadByShelfId(String id);
    List<ShelfActivity> loadByItemId(UUID itemId);
    List<ShelfActivity> loadByShelfIdAndItemId(String shelfId, UUID itemId);
}
