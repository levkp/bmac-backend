package com.bmac.warehouse.adapters.in.facade;

import com.bmac.common.events.BatchForwardedEvent;
import com.bmac.common.facade.ReceiveBatchFacade;
import com.bmac.warehouse.ports.in.order.ReceiveInboundOrderCommand;
import com.bmac.warehouse.ports.in.order.ReceiveInboundOrderUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ReceiveBatchFacadeInAdapter implements ReceiveBatchFacade {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ReceiveInboundOrderUseCase receiveOrder;

    @Autowired
    public ReceiveBatchFacadeInAdapter(ReceiveInboundOrderUseCase receiveOrder) {
        this.receiveOrder = receiveOrder;
    }

    @Override
    public void receive(BatchForwardedEvent event) {
        log.debug("Receiving forwarded batch through facade");

        Map<UUID, Integer> orders = new HashMap<>();
        for(BatchForwardedEvent.OrderLineItem order : event.orderLine()) {
            order.products().forEach((key, value) -> orders.merge(key, value, Integer::sum));
        }

        receiveOrder.receive(
                new ReceiveInboundOrderCommand(event.batchId(), event.batchDate(), LocalDateTime.now(), orders));
    }
}
