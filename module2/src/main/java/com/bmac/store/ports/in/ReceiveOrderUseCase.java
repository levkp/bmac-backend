package com.bmac.store.ports.in;

import java.util.UUID;

public interface ReceiveOrderUseCase {
    // Todo: return Order
    UUID receive(ReceiveOrderCommand command);
}
