package com.bmac.warehouse.domain;

import com.bmac.common.IngredientTemperature;

import java.util.ArrayList;
import java.util.List;

public class Sector {
    private final String id;
    private final IngredientTemperature temperature;
    private final List<Shelf> shelves = new ArrayList<>();

    public Sector(String id, IngredientTemperature temperature) {
        this.id = id;
        this.temperature = temperature;
    }

    public String getId() {
        return id;
    }

    public IngredientTemperature getTemperature() {
        return temperature;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public boolean addShelf(Shelf shelf) {
        return shelves.add(shelf);
    }
}
