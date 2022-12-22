package com.bmac.warehouse.ports.out;

import com.bmac.warehouse.domain.Item;

import java.util.List;

public interface LoadItemPort {
    List<Item> loadAll();
}
