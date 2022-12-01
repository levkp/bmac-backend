package com.bmac.store.domain;

import java.util.ArrayList;
import java.util.List;

public class BatchActivityWindow {
    private final List<BatchActivity> activities = new ArrayList<>();

    public boolean add(BatchActivity activity) {
        return activities.add(activity);
    }
}
