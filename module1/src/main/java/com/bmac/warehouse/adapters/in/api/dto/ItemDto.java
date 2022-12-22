package com.bmac.warehouse.adapters.in.api.dto;

import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.domain.Temperature;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ItemDto {
    @NotNull
    private UUID id;

    @NotNull @NotEmpty
    private String name;

    @Min(0)
    private double minimum;
    @Min(0)
    private double maximum;
    @NotNull
    private Temperature temperature;
    @NotNull
    private Item.Unit unit;

    public ItemDto(UUID id, String name, double minimum, double maximum, Temperature temperature, Item.Unit unit) {
        this.id = id;
        this.name = name;
        this.minimum = minimum;
        this.maximum = maximum;
        this.temperature = temperature;
        this.unit = unit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Item.Unit getUnit() {
        return unit;
    }

    public void setUnit(Item.Unit unit) {
        this.unit = unit;
    }
}
