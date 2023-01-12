package com.bmac.store.ports.in.order;

public interface CancelStoreOrderUseCase {
    void cancel(CancelStoreOrderCommand command);
}
