package com.bmac.store.core;

import com.bmac.common.exception.EntityNotFoundException;
import com.bmac.store.domain.Batch;
import com.bmac.store.ports.in.ForwardBatchCommand;
import com.bmac.store.ports.in.ForwardBatchUseCase;
import com.bmac.store.ports.out.batch.*;
import com.bmac.store.ports.out.product.OrderLoadPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultForwardBatchUseCase implements ForwardBatchUseCase {

    private final BatchLoadPort batchLoader;
    private final BatchUpdatePort batchUpdater;
    private final BatchForwardPort batchForwarder;
    private final BatchActivityLoadPort batchActivityLoader;
    private final BatchActivityCreatePort batchActivityCreator;
    private final OrderLoadPort orderLoader;

    @Autowired
    public DefaultForwardBatchUseCase(BatchLoadPort batchLoader,
                                      BatchUpdatePort batchUpdater,
                                      BatchForwardPort batchForwarder,
                                      BatchActivityLoadPort batchActivityLoader,
                                      BatchActivityCreatePort batchActivityCreator,
                                      OrderLoadPort orderLoader) {
        this.batchLoader = batchLoader;
        this.batchUpdater = batchUpdater;
        this.batchForwarder = batchForwarder;
        this.batchActivityLoader = batchActivityLoader;
        this.batchActivityCreator = batchActivityCreator;
        this.orderLoader = orderLoader;
    }

    @Override
    public void forward(ForwardBatchCommand command) {
        Batch batch = batchLoader.loadByDate(command.timestamp()).orElseThrow(
                () -> new EntityNotFoundException(Batch.class, LocalDate.class, command.timestamp().toString())
        );

        List<UUID> orderIds = batchActivityLoader.loadActiveOrderIdsByBatchId(batch.getId());
        batchActivityCreator.createAll(batch.getId(), batch.forward(orderIds));
        batchUpdater.update(batch);

        try {
            batchForwarder.forward(batch, orderLoader.loadAllByIds(orderIds));
        } catch (JsonProcessingException exception) {
            // Todo
            exception.printStackTrace();
        }
    }
}
