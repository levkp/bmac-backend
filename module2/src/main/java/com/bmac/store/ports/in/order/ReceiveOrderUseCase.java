package com.bmac.store.ports.in.order;

public interface ReceiveOrderUseCase {
    void receive(ReceiveOrderCommand command);
}
