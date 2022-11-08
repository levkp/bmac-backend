package com.bmac.store.core.exception;

import com.bmac.common.cutoff.DailyCutoffTime;

public class CutoffTimePassedException extends RuntimeException {
    public CutoffTimePassedException() {
        super("Unable to fulfill this request, the deadline was at " + DailyCutoffTime.ofToday());
    }
}
