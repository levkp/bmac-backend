package com.bmac.warehouse.domain.order;

import com.bmac.warehouse.domain.item.Item;

import java.util.Map;
import java.util.UUID;

public abstract class Order {
    UUID id;
    private Map<Item, Double> orderLine;
}
