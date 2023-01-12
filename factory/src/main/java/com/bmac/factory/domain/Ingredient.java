package com.bmac.factory.domain;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.IngredientUnit;

import java.util.UUID;

public class Ingredient extends FactoryEntity {
    private String name;
    private IngredientUnit unit;
    private IngredientTemperature temperature;

    public Ingredient(UUID id, String name, IngredientUnit unit, IngredientTemperature temperature) {
        super(id);
        this.name = name;
        this.unit = unit;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public IngredientUnit getUnit() {
        return unit;
    }

    public IngredientTemperature getTemperature() {
        return temperature;
    }
}
