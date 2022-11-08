package com.bmac.warehouse.domain;

import com.bmac.common.domain.Ingredient;

@SuppressWarnings("unused")
public class Item {
    public enum Unit {
        KILOGRAM,
        GRAM,
        LITRE,
        MILLILITRE,
        PIECES
    }

    Ingredient ingredient;
    Shelf location;
    double minimumAmount;
    private Temperature type;
    private Item.Unit unit;
}
