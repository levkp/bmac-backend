package com.bmac.store.ports.in.order;

import java.util.UUID;

public interface ReceiveStoreOrderUseCase {
    // Todo: return Order
    UUID receive(ReceiveStoreOrderCommand command);
}
