package com.bmac.warehouse.domain;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.IngredientUnit;

import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("unused")
public class Item {
    private UUID id;
    private String name;
    private double minimum;
    private double maximum;
    private IngredientTemperature temperature;
    private IngredientUnit unit;
    private int expiryDays;

    public Item(UUID id, String name, double minAmount, double maxAmount, IngredientTemperature temperature, IngredientUnit unit, int expiryDays) {
        this.id = id;
        this.name = name;
        this.minimum = minAmount;
        this.maximum = maxAmount;
        this.temperature = temperature;
        this.unit = unit;
        this.expiryDays = expiryDays;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMinimum() {
        return minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public IngredientTemperature getTemperature() {
        return temperature;
    }

    public IngredientUnit getUnit() {
        return unit;
    }

    public int getExpiryDays() {
        return expiryDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.minimum, minimum) == 0 && Double.compare(item.maximum, maximum) == 0 && expiryDays == item.expiryDays && id.equals(item.id) && name.equals(item.name) && temperature == item.temperature && unit == item.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, minimum, maximum, temperature, unit, expiryDays);
    }
}
