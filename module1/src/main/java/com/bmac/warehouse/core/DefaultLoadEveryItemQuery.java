package com.bmac.warehouse.core;

import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.ports.in.item.LoadEveryItemQuery;
import com.bmac.warehouse.ports.out.item.ItemLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultLoadEveryItemQuery implements LoadEveryItemQuery {

    private final ItemLoadPort itemLoader;

    @Autowired
    public DefaultLoadEveryItemQuery(ItemLoadPort itemLoader) {
        this.itemLoader = itemLoader;
    }

    @Override
    public List<Item> load() {
        return itemLoader.loadAll();
    }
}
