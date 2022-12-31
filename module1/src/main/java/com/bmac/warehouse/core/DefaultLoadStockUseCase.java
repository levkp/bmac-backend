package com.bmac.warehouse.core;

import com.bmac.common.exception.EntityNotFoundException;
import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.ports.in.stock.LoadStockCommand;
import com.bmac.warehouse.ports.in.stock.LoadStockUseCase;
import com.bmac.warehouse.ports.out.item.ItemLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultLoadStockUseCase implements LoadStockUseCase {

    private final ItemLoadPort itemLoader;

    @Autowired
    public DefaultLoadStockUseCase(ItemLoadPort itemLoader) {
        this.itemLoader = itemLoader;
    }

    @Override
    public void load(LoadStockCommand command) {
        Item item =  itemLoader.loadById(command.itemId()).orElseThrow(
                () -> new EntityNotFoundException(Item.class, UUID.class, command.itemId().toString())
        );





    }
}
