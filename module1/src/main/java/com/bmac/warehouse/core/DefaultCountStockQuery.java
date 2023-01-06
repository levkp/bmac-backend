package com.bmac.warehouse.core;

import com.bmac.warehouse.domain.ShelfActivity;
import com.bmac.warehouse.ports.in.item.CountStockQuery;
import com.bmac.warehouse.ports.out.shelf.ShelfActivityLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultCountStockQuery implements CountStockQuery {

    private final ShelfActivityLoadPort shelfActivityLoader;

    @Autowired
    public DefaultCountStockQuery(ShelfActivityLoadPort shelfActivityLoader) {
        this.shelfActivityLoader = shelfActivityLoader;
    }

    @Override
    public double countByShelfIdAndItemId(String shelfId, UUID itemId) {
        return countStock(shelfActivityLoader.loadByShelfIdAndItemId(shelfId, itemId));
    }

    @Override
    public double countByItemId(UUID itemId) {
        return countStock(shelfActivityLoader.loadByItemId(itemId));
    }

    private double countStock(List<ShelfActivity> activities) {
        double stockAmount = 0.0;
        for(ShelfActivity activity : activities) {
            if (activity.action() == ShelfActivity.Action.ADD) {
                stockAmount += activity.amount();
            } else if (activity.action() == ShelfActivity.Action.REMOVE) {
                stockAmount -= activity.amount();
            }
        }
        return stockAmount;
    }
}
