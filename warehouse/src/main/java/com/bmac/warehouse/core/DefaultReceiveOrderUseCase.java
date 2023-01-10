package com.bmac.warehouse.core;

import com.bmac.common.events.BatchForwardedEvent;
import com.bmac.warehouse.domain.Order;
import com.bmac.warehouse.ports.in.ReceiveOrderUseCase;
import com.bmac.warehouse.ports.out.CreateOrderPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DefaultReceiveOrderUseCase implements ReceiveOrderUseCase {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CreateOrderPort orderCreator;

    public DefaultReceiveOrderUseCase(CreateOrderPort orderCreator) {
        this.orderCreator = orderCreator;
    }

    @Override
    public void receive(BatchForwardedEvent event) {
        log.debug("Creating order for batch " + event.batchId());

        Order order = new Order(UUID.randomUUID(), event.batchId(), event.batchDate(), LocalDateTime.now());
        // Todo: order line items

        orderCreator.create(order);
    }
}
