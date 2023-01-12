package com.bmac.store.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Batch {
    public static class ActivityWindow {
        private final List<BatchActivity> activities = new ArrayList<>();
        public void add(BatchActivity activity) {
            activities.add(activity);
        }
        public void addAll(List<BatchActivity> activities) {
            this.activities.addAll(activities);
        }
    }

    private UUID id;
    private LocalDate date;
    private LocalDateTime createTime;
    private LocalDateTime forwardTime;
    private Batch.ActivityWindow activityWindow;

    public Batch() {}

    public Batch(UUID id, LocalDate date, LocalDateTime createTime, Batch.ActivityWindow activityWindow) {
        this.id = id;
        this.date = date;
        this.createTime = createTime;
        this.activityWindow = activityWindow;
    }

    public BatchActivity receiveOrder(StoreOrder order) {
        BatchActivity activity = new BatchActivity(BatchActivity.Action.RECEIVE, order.getId(), LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
    }
    
    public BatchActivity cancelOrder(StoreOrder order) {
        BatchActivity activity = new BatchActivity(BatchActivity.Action.CANCEL, order.getId(), LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
    }

    public List<BatchActivity> forward(List<UUID> orderIds) {
        LocalDateTime forwardTime = LocalDateTime.now();
        setForwardTime(forwardTime);
        List<BatchActivity> activities = orderIds.stream()
                .map(id -> new BatchActivity(BatchActivity.Action.FORWARD, id, forwardTime))
                .toList();
        activityWindow.addAll(activities);
        return activities;
    }

    public void addActivity(BatchActivity activity) {
        activityWindow.add(activity);
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getForwardTime() {
        return forwardTime;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setForwardTime(LocalDateTime forwardTime) {
        this.forwardTime = forwardTime;
    }

    public void setActivityWindow(Batch.ActivityWindow activityWindow) {
        this.activityWindow = activityWindow;
    }
}
