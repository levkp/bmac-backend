package com.bmac.warehouse.domain;

import java.util.UUID;

@SuppressWarnings("unused")
public class Item {
    public enum Unit {
        KILOGRAM,
        GRAM,
        LITRE,
        MILLILITRE,
        PIECES
    }

    private UUID id;
    private String name;
    private double minimum;
    private double maximum;
    private Temperature temperature;
    private Item.Unit unit;

    public Item(UUID id, String name, double minAmount, double maxAmount, Temperature temperature, Unit unit) {
        this.id = id;
        this.name = name;
        this.minimum = minAmount;
        this.maximum = maxAmount;
        this.temperature = temperature;
        this.unit = unit;
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

    public Temperature getTemperature() {
        return temperature;
    }

    public Unit getUnit() {
        return unit;
    }
}
