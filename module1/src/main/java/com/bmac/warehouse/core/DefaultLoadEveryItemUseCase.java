package com.bmac.warehouse.core;

import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.ports.in.LoadEveryItemUseCase;
import com.bmac.warehouse.ports.out.LoadItemPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultLoadEveryItemUseCase implements LoadEveryItemUseCase {

    private final LoadItemPort itemLoader;

    @Autowired
    public DefaultLoadEveryItemUseCase(LoadItemPort itemLoader) {
        this.itemLoader = itemLoader;
    }

    @Override
    public List<Item> load() {
        return itemLoader.loadAll();
    }
}
