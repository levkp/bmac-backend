package com.bmac.store.ports.in.order;

public interface ReceiveOrderUseCase {
    // Todo: return Order
    void receive(ReceiveOrderCommand command);
}
