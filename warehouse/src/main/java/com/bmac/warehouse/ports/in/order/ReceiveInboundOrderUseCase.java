package com.bmac.warehouse.ports.in.order;

public interface ReceiveInboundOrderUseCase {
    void receive(ReceiveInboundOrderCommand command);
}
