package com.bmac.store.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class Batch {
    private final UUID uuid;
    private final LocalDate date;

    private final LocalDateTime createTime;

    private LocalDateTime forwardTime;
    private final BatchActivityWindow activityWindow;

    public Batch(UUID uuid, LocalDate date, LocalDateTime createTime, LocalDateTime forwardTime,
                 BatchActivityWindow activities) {
        this.uuid = uuid;
        this.date = date;
        this.createTime = createTime;
        this.forwardTime = forwardTime;
        this.activityWindow = activities;
    }

    public Batch(UUID uuid, LocalDate date, LocalDateTime createTime, BatchActivityWindow activityWindow) {
        this.uuid = uuid;
        this.date = date;
        this.createTime = createTime;
        this.activityWindow = activityWindow;
    }

    public BatchActivity addOrder(Order order) {
        BatchActivity activity = new BatchActivity(BatchActivity.Action.RECEIVE, order.getUuid(), LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
    }
    
    public BatchActivity cancelOrder(Order order) {
        BatchActivity activity = new BatchActivity(BatchActivity.Action.CANCEL, order.getUuid(), LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
    }

    public UUID getUuid() {
        return uuid;
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
}
