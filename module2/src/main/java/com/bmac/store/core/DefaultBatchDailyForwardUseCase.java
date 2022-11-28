package com.bmac.store.core;

import com.bmac.store.ports.out.BatchDailyForwardPort;
import com.bmac.store.ports.out.BatchLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class DefaultBatchDailyForwardUseCase {

    private BatchLoadPort batchLoadPort;
    private List<BatchDailyForwardPort> forwardPorts;

    public DefaultBatchDailyForwardUseCase(BatchLoadPort batchLoadPort) {
        this.batchLoadPort = batchLoadPort;
    }

    @Scheduled(cron = "1 22 * * *")
    public void forwardDailyBatch() {

    }
}
