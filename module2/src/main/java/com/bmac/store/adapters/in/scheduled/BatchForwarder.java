package com.bmac.store.adapters.in.scheduled;

import com.bmac.store.core.exception.StoreEntityNotFoundException;
import com.bmac.store.ports.in.batch.BatchForwardCommand;
import com.bmac.store.ports.in.batch.BatchForwardUseCase;
import com.bmac.store.ports.out.batch.DailyBatchUuidQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@Component
public class BatchForwarder {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BatchForwardUseCase forwardOrder;
    private final DailyBatchUuidQuery dailyBatchUuidQuery;

    @Autowired
    public BatchForwarder(BatchForwardUseCase forwardOrder, DailyBatchUuidQuery dailyBatchUuidQuery) {
        this.forwardOrder = forwardOrder;
        this.dailyBatchUuidQuery = dailyBatchUuidQuery;
    }

    @Scheduled(cron = "1 22 * * *")
    public void forwardDailyBatch() {
        log.info("Forwarding daily batch");
        forwardOrder.forward(new BatchForwardCommand(dailyBatchUuidQuery.getDailyBatchUuid()));
        log.info("Forwarded daily batch");
    }

    @ExceptionHandler(StoreEntityNotFoundException.class)
    public void handleStoreEntityNotFoundException(StoreEntityNotFoundException exception) {
        log.error(exception.getMessage());
    }
}
