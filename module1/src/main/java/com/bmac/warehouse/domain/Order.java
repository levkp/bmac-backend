package com.bmac.warehouse.domain;

import com.bmac.warehouse.domain.Item;

import java.util.Map;
import java.util.UUID;

public abstract class Order {
    UUID id;
    private Map<Item, Double> orderLine;
}
