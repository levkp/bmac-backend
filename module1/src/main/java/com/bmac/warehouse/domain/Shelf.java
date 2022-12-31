package com.bmac.warehouse.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Shelf {
    public static class ActivityWindow {
        private final List<ShelfActivity> activities = new ArrayList<>();

        public boolean add(ShelfActivity activity) {
            return activities.add(activity);
        }
    }

    private final String id;

    private Shelf.ActivityWindow activityWindow;

    public Shelf(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public ActivityWindow getActivityWindow() {
        return activityWindow;
    }

    public ShelfActivity load(UUID itemId, double amount) {
        ShelfActivity activity = new ShelfActivity(
                ShelfActivity.Action.LOAD, UUID.randomUUID(), id, itemId, amount, LocalDateTime.now()
        );
        activityWindow.add(activity);
        return activity;
    }


}
