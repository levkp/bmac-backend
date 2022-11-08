package com.bmac.store.core;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.store.core.exception.CutoffTimePassedException;
import com.bmac.store.core.exception.StoreEntityNotFoundException;
import com.bmac.store.domain.Order;
import com.bmac.store.ports.in.CancelOrderCommand;
import com.bmac.store.ports.in.CancelOrderUseCase;
import com.bmac.store.ports.out.BatchActivityCreatePort;
import com.bmac.store.ports.out.OrderLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultCancelOrderUseCase implements CancelOrderUseCase {

    private final OrderLoadPort orderLoader;
    private final BatchActivityCreatePort batchActivityCreator;

    @Autowired
    public DefaultCancelOrderUseCase(OrderLoadPort orderLoader, BatchActivityCreatePort batchActivityCreator) {
        this.orderLoader = orderLoader;
        this.batchActivityCreator = batchActivityCreator;
    }

    @Override
    public void cancel(CancelOrderCommand command) {
        Order order = orderLoader.loadById(command.id()).orElseThrow(
                () -> new StoreEntityNotFoundException(Order.class, UUID.class, command.id().toString())
        );

        if (order.getTimestamp().isBefore(DailyCutoffTime.ofToday()) && DailyCutoffTime.hasPassed()) {
            throw new CutoffTimePassedException();
        }
        // If the order was received after the daily cutoff time, it can be cancelled for sure, since it belongs to the
        // batch of tomorrow
        batchActivityCreator.create(order.getBatch().getId(), order.getBatch().cancelOrder(order));
    }
}
