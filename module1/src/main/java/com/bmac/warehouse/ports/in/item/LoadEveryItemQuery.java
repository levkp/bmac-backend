package com.bmac.warehouse.ports.in.item;

import com.bmac.warehouse.domain.Item;
import java.util.List;

public interface LoadEveryItemQuery {
    List<Item> load();
}
