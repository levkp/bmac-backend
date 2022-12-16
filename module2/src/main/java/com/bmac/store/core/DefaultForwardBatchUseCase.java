package com.bmac.store.core;

import com.bmac.store.core.exception.StoreEntityNotFoundException;
import com.bmac.store.domain.Batch;
import com.bmac.store.ports.in.ForwardBatchCommand;
import com.bmac.store.ports.in.ForwardBatchUseCase;
import com.bmac.store.ports.out.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DefaultForwardBatchUseCase implements ForwardBatchUseCase {

    private final BatchLoadPort batchLoader;
    private final BatchUpdatePort batchUpdater;
    private final BatchForwardPort batchForwarder;
    private final BatchActivityLoadPort batchActivityLoader;
    private final OrderLoadPort orderLoader;

    @Autowired
    public DefaultForwardBatchUseCase(BatchLoadPort batchLoader,
                                      BatchUpdatePort batchUpdater,
                                      BatchForwardPort batchForwarder,
                                      BatchActivityLoadPort batchActivityLoader,
                                      OrderLoadPort orderLoader) {
        this.batchLoader = batchLoader;
        this.batchUpdater = batchUpdater;
        this.batchForwarder = batchForwarder;
        this.batchActivityLoader = batchActivityLoader;
        this.orderLoader = orderLoader;
    }

    @Override
    public void forward(ForwardBatchCommand command) {
        Batch batch = batchLoader.loadByDate(command.timestamp()).orElseThrow(
                () -> new StoreEntityNotFoundException(Batch.class, LocalDate.class, command.timestamp().toString())
        );

        batchActivityLoader.loadActiveOrderIdsByBatchId(batch.getId());

        batchUpdater.update(batch);
        try {
            batchForwarder.forward(batch, orderLoader.loadAllByBatchId(batch.getId()));
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }
    }
}
