package com.bmac.store.exception;

import com.bmac.common.helpers.DailyCutoffTime;

public class CutoffTimePassedException extends RuntimeException {
    public CutoffTimePassedException() {
        super("Unable to fulfill this request, the deadline was at " + DailyCutoffTime.ofToday());
    }
}
