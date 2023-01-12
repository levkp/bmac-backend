package com.bmac.common.facade;

import com.bmac.common.events.BatchForwardedEvent;

public interface ReceiveBatchFacade {
    void receive(BatchForwardedEvent event);
}
