package be.kdg.prog6.thebakery.warehouse.domain;

import java.util.UUID;

@SuppressWarnings("unused")
public class Ingredient {
    UUID id;
    double minimumAmount;
    private IngredientType type;
    private IngredientUnit unit;
    private String name;
}
