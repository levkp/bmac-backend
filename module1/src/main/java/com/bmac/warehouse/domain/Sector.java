package com.bmac.warehouse.domain;

import java.util.ArrayList;
import java.util.List;

public class Sector {
    private final String id;
    private final Temperature temperature;
    private final List<Shelf> shelves = new ArrayList<>();

    public Sector(Temperature temperature, int numberOfShelves) {
        this.temperature = temperature;
        this.id = Sector.makeId(this);
        for(int i = 0; i < numberOfShelves; i++) {
            shelves.add(new Shelf(id + (shelves.size() + 1)));
        }
    }

    public String getId() {
        return id;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    private static char nextSectorChar = 'A';
    private static String makeId(Sector sector) {
        return sector.getTemperature().name().substring(0, 3) + '.' + nextSectorChar++;
    }
}
