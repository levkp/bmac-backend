package com.bmac.warehouse.core;

import com.bmac.common.exception.EntityNotFoundException;
import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.domain.Shelf;
import com.bmac.warehouse.exception.OutOfStockException;
import com.bmac.warehouse.ports.in.stock.ChangeStockCommand;
import com.bmac.warehouse.ports.in.stock.ChangeStockUseCase;
import com.bmac.warehouse.ports.in.stock.CountStockQuery;
import com.bmac.warehouse.ports.out.item.LoadItemPort;
import com.bmac.warehouse.ports.out.shelf.ShelfActivityCreatePort;
import com.bmac.warehouse.ports.out.shelf.ShelfLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultChangeStockUseCase implements ChangeStockUseCase {

    private final LoadItemPort itemLoader;
    private final ShelfLoadPort shelfLoader;
    private final ShelfActivityCreatePort shelfActivityCreator;
    private final CountStockQuery stockCounter;

    @Autowired
    public DefaultChangeStockUseCase(LoadItemPort itemLoader,
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
                () -> new EntityNotFoundException(Item.class, UUID.class, itemId.toString())
        );
    }

    private Shelf loadShelf(String shelfId) {
        return shelfLoader.loadById(shelfId).orElseThrow(
                () -> new EntityNotFoundException(Shelf.class, shelfId)
        );
    }
}
