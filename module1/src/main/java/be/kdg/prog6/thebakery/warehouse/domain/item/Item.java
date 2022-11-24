package be.kdg.prog6.thebakery.warehouse.domain.item;

import java.util.UUID;

@SuppressWarnings("unused")
public class Item {
    UUID id;
    double minimumAmount;
    private ItemType type;
    private ItemUnit unit;
    private String name;
}
