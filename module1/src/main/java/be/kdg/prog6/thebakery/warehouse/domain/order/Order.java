package be.kdg.prog6.thebakery.warehouse.domain.order;

import be.kdg.prog6.thebakery.warehouse.domain.item.Item;

import java.util.Map;
import java.util.UUID;

public abstract class Order {
    UUID id;
    private Map<Item, Double> orderLine;
}
