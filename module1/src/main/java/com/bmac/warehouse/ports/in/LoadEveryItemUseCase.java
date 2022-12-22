package com.bmac.warehouse.ports.in;

import com.bmac.warehouse.domain.Item;

import java.util.List;

public interface LoadEveryItemUseCase {
    List<Item> load();
}
