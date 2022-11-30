package com.bmac.store.core;

import com.bmac.store.adapters.out.db.batch.BatchActivityJpaEntity;
import com.bmac.store.ports.in.batch.BatchForwardCommand;
import com.bmac.store.ports.in.batch.BatchForwardUseCase;
import com.bmac.store.ports.out.batch.BatchActivityCreatePort;
import com.bmac.store.ports.out.batch.BatchForwardPort;
import com.bmac.store.ports.out.order.OrderLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultBatchForwardUseCase implements BatchForwardUseCase {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BatchActivityCreatePort batchCreator;
    private final BatchForwardPort batchForwarder;
    private final OrderLoadPort orderLoader;

    @Autowired
    public DefaultBatchForwardUseCase(BatchActivityCreatePort batchCreator,
                                      BatchForwardPort batchForwarder,
                                      OrderLoadPort orderLoader) {
        this.batchCreator = batchCreator;
        this.batchForwarder = batchForwarder;
        this.orderLoader = orderLoader;
    }

    @Override
    public void forward(BatchForwardCommand command) {
        orderLoader.loadAllByBatchUuid(command.batchUuid());

        batchForwarder.forward(command.batchUuid());
        batchCreator.createBatchActivity(command.batchUuid(), BatchActivityJpaEntity.BatchAction.FORWARD);
    }
}
