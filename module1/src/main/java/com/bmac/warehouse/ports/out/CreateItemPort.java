package com.bmac.warehouse.ports.out;

import com.bmac.warehouse.domain.Item;

public interface CreateItemPort {
    void create(Item item);
}
