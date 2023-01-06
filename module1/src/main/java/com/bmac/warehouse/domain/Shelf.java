package com.bmac.warehouse.domain;

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

    private final Shelf.ActivityWindow activityWindow = new Shelf.ActivityWindow();

    public Shelf(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public ActivityWindow getActivityWindow() {
        return activityWindow;
    }


    public ShelfActivity addStock(UUID itemId, double amount) {
        return changeStock(itemId, amount, ShelfActivity.Action.ADD);
    }

    public ShelfActivity removeStock(UUID itemId, double amount) {
        return changeStock(itemId, amount, ShelfActivity.Action.REMOVE);
    }

    private ShelfActivity changeStock(UUID itemId, double amount, ShelfActivity.Action action) {
        ShelfActivity activity = new ShelfActivity(action, id, itemId, amount, LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
    }

}
