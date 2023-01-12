package com.bmac.warehouse.ports.in.item;

import com.bmac.warehouse.domain.Item;

public interface CreateItemUseCase {
    Item create(CreateItemCommand command);
}
