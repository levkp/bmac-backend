package com.bmac.warehouse.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shelf {
    public static class ActivityWindow {
        private final List<ShelfActivity> activities = new ArrayList<>();

        public boolean add(ShelfActivity activity) {
            return activities.add(activity);
        }
    }

    String id;

    Shelf.ActivityWindow activityWindow;

    private Shelf() { }

    public Shelf(String id) {
        this.id = id;
    }

    public LocalDate getClosestExpiry() {
        return LocalDate.now();
    }
}
