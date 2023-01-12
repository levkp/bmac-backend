package com.bmac.warehouse.adapters.in.api.dto;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.IngredientUnit;

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
    private IngredientTemperature temperature;
    @NotNull
    private IngredientUnit unit;

    public ItemDto(UUID id, String name, double minimum, double maximum, IngredientTemperature temperature, IngredientUnit unit) {
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


}
