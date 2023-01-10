package com.bmac.warehouse.adapters.in.facade;

import com.bmac.common.facade.ReceiveBatchCommand;
import com.bmac.common.facade.ReceiveBatchFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("facade")
@Component
public class BatchReceiveFacadeAdapter implements ReceiveBatchFacade {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void receive(ReceiveBatchCommand command) {
        log.debug("Receiving forwarded batch through facade");

    }
}
