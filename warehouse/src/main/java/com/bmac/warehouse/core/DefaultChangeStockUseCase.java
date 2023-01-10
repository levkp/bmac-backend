package com.bmac.warehouse.core;

import com.bmac.warehouse.exception.OutOfStockException;
import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.domain.Shelf;
import com.bmac.warehouse.exception.WarehouseEntityNotFoundException;
import com.bmac.warehouse.ports.in.CountStockQuery;
import com.bmac.warehouse.ports.in.ChangeStockCommand;
import com.bmac.warehouse.ports.in.ChangeStockUseCase;
import com.bmac.warehouse.ports.out.ItemLoadPort;
import com.bmac.warehouse.ports.out.ShelfActivityCreatePort;
import com.bmac.warehouse.ports.out.ShelfLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultChangeStockUseCase implements ChangeStockUseCase {

    private final ItemLoadPort itemLoader;
    private final ShelfLoadPort shelfLoader;
    private final ShelfActivityCreatePort shelfActivityCreator;
    private final CountStockQuery stockCounter;

    @Autowired
    public DefaultChangeStockUseCase(ItemLoadPort itemLoader,
                                     ShelfLoadPort shelfLoader,
                                     ShelfActivityCreatePort shelfActivityCreator,
                                     CountStockQuery stockCounter) {
        this.itemLoader = itemLoader;
        this.shelfLoader = shelfLoader;
        this.shelfActivityCreator = shelfActivityCreator;
        this.stockCounter = stockCounter;
    }

    @Override
    public void add(ChangeStockCommand command) {
        shelfActivityCreator.create(
                loadShelf(command.shelfId()).addStock(loadItem(command.itemId()).getId(), command.amount())
        );
    }

    @Override
    public void remove(ChangeStockCommand command) throws OutOfStockException {
        if (stockCounter.countByShelfIdAndItemId(command.shelfId(), command.itemId()) > 0.0) {
            shelfActivityCreator.create(
                    loadShelf(command.shelfId()).removeStock(loadItem(command.itemId()).getId(), command.amount())
            );
        } else {
            throw new OutOfStockException(command.shelfId(), command.itemId());
        }
    }

    private Item loadItem(UUID itemId) {
        return itemLoader.loadById(itemId).orElseThrow(
                () -> new WarehouseEntityNotFoundException(Item.class, UUID.class, itemId.toString())
        );
    }

    private Shelf loadShelf(String shelfId) {
        return shelfLoader.loadById(shelfId).orElseThrow(
                () -> new WarehouseEntityNotFoundException(Shelf.class, shelfId)
        );
    }
}
