package com.bmac.warehouse.ports.in;

import com.bmac.common.events.BatchForwardedEvent;

public interface ReceiveOrderUseCase {
    void receive(BatchForwardedEvent event);
}
