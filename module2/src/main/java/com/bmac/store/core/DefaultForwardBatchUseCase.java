package com.bmac.store.core;

import com.bmac.store.core.exception.StoreEntityNotFoundException;
import com.bmac.store.domain.Batch;
import com.bmac.store.ports.in.ForwardBatchCommand;
import com.bmac.store.ports.in.ForwardBatchUseCase;
import com.bmac.store.ports.out.BatchForwardPort;
import com.bmac.store.ports.out.BatchLoadPort;
import com.bmac.store.ports.out.BatchUpdatePort;
import com.bmac.store.ports.out.OrderLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DefaultForwardBatchUseCase implements ForwardBatchUseCase {

    private final BatchLoadPort batchLoader;
    private final BatchUpdatePort batchUpdater;
    private final BatchForwardPort batchForwarder;
    private final OrderLoadPort orderLoader;



    @Autowired
    public DefaultForwardBatchUseCase(BatchLoadPort batchLoader,
                                      BatchUpdatePort batchUpdater,
                                      BatchForwardPort batchForwarder,
                                      OrderLoadPort orderLoader) {
        this.batchLoader = batchLoader;
        this.batchUpdater = batchUpdater;
        this.batchForwarder = batchForwarder;
        this.orderLoader = orderLoader;
    }

    @Override
    public void forward(ForwardBatchCommand command) {
        Batch batch = batchLoader.loadByDateTime(command.timestamp()).orElseThrow(
                () -> new StoreEntityNotFoundException(Batch.class, LocalDate.class, command.timestamp().toString())
        );
        batch.setForwardTime(LocalDateTime.now());
        batchUpdater.update(batch);
        // Todo: filter cancelled orders
        batchForwarder.forward(batch, orderLoader.loadAllByBatchId(batch.getId()));
    }
}
