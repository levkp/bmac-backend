package com.bmac.store.core;

import com.bmac.common.exception.EntityNotFoundException;
import com.bmac.store.domain.Batch;
import com.bmac.store.ports.in.batch.ForwardBatchCommand;
import com.bmac.store.ports.in.batch.ForwardBatchUseCase;
import com.bmac.store.ports.out.batch.*;
import com.bmac.store.ports.out.order.LoadStoreOrderPort;
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
    private final List<BatchForwardPort> batchForwarders;
    private final BatchActivityLoadPort batchActivityLoader;
    private final BatchActivityCreatePort batchActivityCreator;
    private final LoadStoreOrderPort orderLoader;

    @Autowired
    public DefaultForwardBatchUseCase(BatchLoadPort batchLoader,
                                      BatchUpdatePort batchUpdater,
                                      List<BatchForwardPort> batchForwarders,
                                      BatchActivityLoadPort batchActivityLoader,
                                      BatchActivityCreatePort batchActivityCreator,
                                      LoadStoreOrderPort orderLoader) {
        this.batchLoader = batchLoader;
        this.batchUpdater = batchUpdater;
        this.batchForwarders = batchForwarders;
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

        batchForwarders.forEach(forwarder -> {
            try {
                forwarder.forward(batch, orderLoader.loadAllByIds(orderIds));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
