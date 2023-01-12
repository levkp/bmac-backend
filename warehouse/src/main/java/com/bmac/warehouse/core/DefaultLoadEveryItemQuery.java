package com.bmac.warehouse.core;

import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.ports.in.item.LoadEveryItemQuery;
import com.bmac.warehouse.ports.out.item.LoadItemPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultLoadEveryItemQuery implements LoadEveryItemQuery {

    private final LoadItemPort itemLoader;

    @Autowired
    public DefaultLoadEveryItemQuery(LoadItemPort itemLoader) {
        this.itemLoader = itemLoader;
    }

    @Override
    public List<Item> load() {
        return itemLoader.loadAll();
    }
}
