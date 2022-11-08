package com.bmac.store.ports.in;

public interface ForwardBatchUseCase {
    void forward(ForwardBatchCommand command);
}
