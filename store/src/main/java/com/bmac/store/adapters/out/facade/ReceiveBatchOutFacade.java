package com.bmac.store.adapters.out.facade;

import com.bmac.common.events.BatchForwardedEvent;
import com.bmac.common.facade.ReceiveBatchFacade;
import com.bmac.store.domain.Batch;
import com.bmac.store.domain.StoreOrder;
import com.bmac.store.ports.out.batch.BatchForwardPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReceiveBatchOutFacade implements BatchForwardPort {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final List<ReceiveBatchFacade> facades;

    @Autowired
    public ReceiveBatchOutFacade(@Autowired(required = false) List<ReceiveBatchFacade> facades) {
        this.facades = facades;
    }

    @Override
    public void forward(Batch batch, List<StoreOrder> orderLine) throws JsonProcessingException {
        log.info("Forwarding batch through " + facades.size() + " facade(s)");

        List<BatchForwardedEvent.OrderLineItem> items = orderLine.stream().map(
                order -> new BatchForwardedEvent.OrderLineItem(
                        order.getId(),
                        order.getTimestamp(),
                        order.getOrderedProductsWithUUIDKey())
        ).toList();

        facades.forEach(facade -> facade.receive(new BatchForwardedEvent(batch.getId(), batch.getDate(), items)));
    }
}
