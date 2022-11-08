package com.bmac.store.ports.in;

public interface CancelOrderUseCase {
    void cancel(CancelOrderCommand command);
}
