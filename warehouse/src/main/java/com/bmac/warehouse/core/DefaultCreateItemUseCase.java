package com.bmac.warehouse.core;

import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.ports.in.item.CreateItemCommand;
import com.bmac.warehouse.ports.in.item.CreateItemUseCase;
import com.bmac.warehouse.ports.out.item.ItemCreatePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultCreateItemUseCase implements CreateItemUseCase {

    private final ItemCreatePort itemCreator;

    @Autowired
    public DefaultCreateItemUseCase(ItemCreatePort itemCreator) {
        this.itemCreator = itemCreator;
    }

    @Override
    public Item create(CreateItemCommand command) {
        Item item = new Item(
                UUID.randomUUID(),
                command.name(),
                command.minimum(),
                command.maximum(),
                command.temperature(),
                command.unit(),
                command.expiryDays()
        );
        itemCreator.create(item);
        return item;
    }
}
