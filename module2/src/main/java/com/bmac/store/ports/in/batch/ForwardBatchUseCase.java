package com.bmac.store.ports.in.batch;

public interface ForwardBatchUseCase {
    void forward(ForwardBatchCommand command);
}
