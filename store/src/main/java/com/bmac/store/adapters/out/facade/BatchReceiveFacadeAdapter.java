package com.bmac.store.adapters.out.facade;

import com.bmac.common.events.BatchForwardedEvent;
import com.bmac.common.facade.ReceiveBatchCommand;
import com.bmac.common.facade.ReceiveBatchFacade;
import com.bmac.store.domain.Batch;
import com.bmac.store.domain.Order;
import com.bmac.store.ports.out.batch.BatchForwardPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("facade")
@Component
public class BatchReceiveFacadeAdapter implements BatchForwardPort {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ReceiveBatchFacade facade;

    @Autowired
    public BatchReceiveFacadeAdapter(@Autowired(required = false) ReceiveBatchFacade facade) {
        this.facade = facade;
    }

    @Override
    public void forward(Batch batch, List<Order> orderLine) throws JsonProcessingException {
        log.debug("Forwarding batch through facade");

        List<BatchForwardedEvent.OrderLineItem> items = orderLine.stream().map(
                order -> new BatchForwardedEvent.OrderLineItem(order.getId(), order.getTimestamp(), order.getOrderedProductsWithUUIDKey())
        ).toList();

        facade.receive(new ReceiveBatchCommand(new BatchForwardedEvent(batch.getId(), batch.getDate(), items)));
    }
}
