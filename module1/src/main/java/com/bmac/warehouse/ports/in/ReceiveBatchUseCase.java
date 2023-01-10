package com.bmac.warehouse.ports.in;

public interface ReceiveBatchUseCase {
    void receive(ReceiveBatchCommand command);
}
