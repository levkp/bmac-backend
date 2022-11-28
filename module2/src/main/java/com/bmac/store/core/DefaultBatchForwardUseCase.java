package com.bmac.store.core;

import com.bmac.store.adapters.out.db.batch.BatchActivityEntity;
import com.bmac.store.ports.in.batch.BatchForwardCommand;
import com.bmac.store.ports.in.batch.BatchForwardUseCase;
import com.bmac.store.ports.out.batch.BatchActivityCreatePort;
import com.bmac.store.ports.out.batch.BatchForwardPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultBatchForwardUseCase implements BatchForwardUseCase {
    private final Logger log = LoggerFactory.getLogger(getClass());
    BatchActivityCreatePort batchActivityCreatePort;
    BatchForwardPort batchForwardPort;

    @Override
    public void forward(BatchForwardCommand command) {
        // Todo: move to publisher
        batchActivityCreatePort.createBatchActivity(command.batchUuid(), BatchActivityEntity.BatchAction.FORWARD);

        batchForwardPort.forward(command.batchUuid());




    }
}
