package com.bmac.store.ports.in;

public interface ReceiveOrderUseCase {
    void receive(ReceiveOrderCommandMVP command);
}
