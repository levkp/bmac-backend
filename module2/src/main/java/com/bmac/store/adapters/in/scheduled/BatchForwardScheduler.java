package com.bmac.store.adapters.in.scheduled;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.store.exception.StoreEntityNotFoundException;
import com.bmac.store.ports.in.ForwardBatchCommand;
import com.bmac.store.ports.in.ForwardBatchUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@Component
public class BatchForwardScheduler {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ForwardBatchUseCase forwardBatch;

    @Autowired
    public BatchForwardScheduler(ForwardBatchUseCase forwardBatch) {
        this.forwardBatch = forwardBatch;
    }

    @Scheduled(cron = DailyCutoffTime.CRON)
    public void forwardDailyBatch() {
        log.info("Forwarding daily batch");
        forwardBatch.forward(new ForwardBatchCommand(LocalDate.now()));
        log.info("Forwarded daily batch");
    }

    @ExceptionHandler(StoreEntityNotFoundException.class)
    public void handleStoreEntityNotFoundException(StoreEntityNotFoundException exception) {
        log.error(exception.getMessage()); // Todo
    }
}
