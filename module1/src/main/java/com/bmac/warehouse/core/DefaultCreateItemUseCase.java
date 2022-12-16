package com.bmac.warehouse.core;

import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.ports.in.CreateItemCommand;
import com.bmac.warehouse.ports.in.CreateItemUseCase;
import com.bmac.warehouse.ports.out.CreateItemPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultCreateItemUseCase implements CreateItemUseCase {

    private final CreateItemPort itemCreator;

    @Autowired
    public DefaultCreateItemUseCase(CreateItemPort itemCreator) {
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
                command.unit()
        );
        itemCreator.create(item);
        return item;
    }
}
