package com.bmac.warehouse.core;

import com.bmac.common.exception.EntityNotFoundException;
import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.domain.Shelf;
import com.bmac.warehouse.ports.in.stock.ChangeStockCommand;
import com.bmac.warehouse.ports.in.stock.ChangeStockUseCase;
import com.bmac.warehouse.ports.out.item.ItemLoadPort;
import com.bmac.warehouse.ports.out.shelf.ShelfActivityCreatePort;
import com.bmac.warehouse.ports.out.shelf.ShelfLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultChangeStockUseCase implements ChangeStockUseCase {

    private final ItemLoadPort itemLoader;
    private final ShelfLoadPort shelfLoader;
    private final ShelfActivityCreatePort shelfActivityCreator;

    @Autowired
    public DefaultChangeStockUseCase(ItemLoadPort itemLoader,
                                     ShelfLoadPort shelfLoader,
                                     ShelfActivityCreatePort shelfActivityCreator) {
        this.itemLoader = itemLoader;
        this.shelfLoader = shelfLoader;
        this.shelfActivityCreator = shelfActivityCreator;
    }

    @Override
    public void add(ChangeStockCommand command) {
        shelfActivityCreator.create(loadShelf(command.shelfId()).loadStock(loadItem(command.itemId()).getId(), command.amount()));
    }

    @Override
    public void remove(ChangeStockCommand command) {


    }

    private Item loadItem(UUID itemId) {
        return itemLoader.loadById(itemId).orElseThrow(
                () -> new EntityNotFoundException(Item.class, UUID.class, itemId.toString())
        );
    }

    private Shelf loadShelf(String shelfId) {
        return shelfLoader.loadById(shelfId).orElseThrow(
                () -> new EntityNotFoundException(Shelf.class, shelfId)
        );
    }
}
