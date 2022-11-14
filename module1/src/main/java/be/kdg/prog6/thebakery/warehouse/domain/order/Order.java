package be.kdg.prog6.thebakery.warehouse.domain.order;

import be.kdg.prog6.thebakery.warehouse.domain.Ingredient;

import java.util.List;
import java.util.UUID;

public abstract class Order {
    UUID id;
    private List<Ingredient> orderLine;
}
