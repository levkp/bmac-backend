package com.bmac.store.core;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.store.core.exception.CutoffTimePassedException;
import com.bmac.store.core.exception.OrderAlreadyCancelledException;
import com.bmac.store.core.exception.StoreEntityNotFoundException;
import com.bmac.store.domain.BatchActivity;
import com.bmac.store.domain.Order;
import com.bmac.store.ports.in.CancelOrderCommand;
import com.bmac.store.ports.in.CancelOrderUseCase;
import com.bmac.store.ports.out.BatchActivityCreatePort;
import com.bmac.store.ports.out.BatchActivityLoadPort;
import com.bmac.store.ports.out.OrderLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultCancelOrderUseCase implements CancelOrderUseCase {

    private final OrderLoadPort orderLoader;
    private final BatchActivityCreatePort batchActivityCreator;
    private final BatchActivityLoadPort batchActivityLoader;

    @Autowired
    public DefaultCancelOrderUseCase(OrderLoadPort orderLoader,
                                     BatchActivityCreatePort batchActivityCreator,
                                     BatchActivityLoadPort batchActivityLoader) {
        this.orderLoader = orderLoader;
        this.batchActivityCreator = batchActivityCreator;
        this.batchActivityLoader = batchActivityLoader;
    }

    @Override
    public void cancel(CancelOrderCommand command) {
        Order order = orderLoader.loadById(command.id()).orElseThrow(
                () -> new StoreEntityNotFoundException(Order.class, UUID.class, command.id().toString())
        );

        batchActivityLoader.loadByOrderId(order.getId()).forEach(activity -> {
            if (activity.action() == BatchActivity.Action.CANCEL) {
                throw new OrderAlreadyCancelledException(activity.orderId(), activity.timestamp());
            }
        });

        // Todo: this needs to be tested properly
        if (order.getTimestamp().isBefore(DailyCutoffTime.ofToday()) && DailyCutoffTime.hasPassed()) {
            throw new CutoffTimePassedException();
        }
        // If the order was received after the daily cutoff time, it can be cancelled for sure, since it belongs to the
        // batch of tomorrow
        batchActivityCreator.create(order.getBatch().getId(), order.getBatch().cancelOrder(order));
    }
}
