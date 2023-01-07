package com.bmac.warehouse.adapters.in.shell;

import com.bmac.warehouse.ports.in.item.CountStockQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.UUID;

@Profile("shell")
@ShellComponent
@SuppressWarnings("unused")
public class WarehouseShell {

    private final CountStockQuery stockCounter;

    @Autowired
    public WarehouseShell(CountStockQuery stockCounter) {
        this.stockCounter = stockCounter;
    }

    @ShellMethod(key = "countStock", value = "Count stock by item id")
    public double countStock(@ShellOption(defaultValue = "0") String itemId, @ShellOption(defaultValue = "0") String shelfId) {

        if (!shelfId.equals("0")) {
            return stockCounter.countByShelfIdAndItemId(shelfId, UUID.fromString(itemId));
        }

        return stockCounter.countByItemId(UUID.fromString(itemId));

//        if (!shelfId.equals("0") && !itemId.equals("0")) {
//            System.out.println(stockCounter.countByShelfIdAndItemId(shelfId, UUID.fromString(itemId)));
//        } else if (!itemId.equals("")) {
//            System.out.println(stockCounter.countByItemId(UUID.fromString(itemId)));
//        } else {
//            System.out.println("You need to specify the shelf id, item id or both");
//        }
    }
}
