package com.bmac.store.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Batch {
    private UUID id;
    private LocalDate date;

    private LocalDateTime createTime;

    private LocalDateTime forwardTime;
    private BatchActivityWindow activityWindow;

    public Batch() {}

    public Batch(UUID id, LocalDate date, LocalDateTime createTime, BatchActivityWindow activityWindow) {
        this.id = id;
        this.date = date;
        this.createTime = createTime;
        this.activityWindow = activityWindow;
    }

    public BatchActivity addOrder(Order order) {
        BatchActivity activity = new BatchActivity(BatchActivity.Action.RECEIVE, order.getId(), LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
    }
    
    public BatchActivity cancelOrder(Order order) {
        BatchActivity activity = new BatchActivity(BatchActivity.Action.CANCEL, order.getId(), LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
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

    public BatchActivityWindow getActivityWindow() {
        return activityWindow;
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

    public void setActivityWindow(BatchActivityWindow activityWindow) {
        this.activityWindow = activityWindow;
    }
}
