package com.bmac.warehouse.ports.out;

import com.bmac.warehouse.domain.Item;

public interface ItemCreatePort {
    void create(Item item);
}
