package com.bmac.store.core;

import com.bmac.common.helpers.DailyCutoffTime;
import com.bmac.common.exception.EntityNotFoundException;
import com.bmac.store.exception.CutoffTimePassedException;
import com.bmac.store.exception.OrderAlreadyCancelledException;
import com.bmac.store.domain.BatchActivity;
import com.bmac.store.domain.StoreOrder;
import com.bmac.store.ports.in.order.CancelStoreOrderCommand;
import com.bmac.store.ports.in.order.CancelStoreOrderUseCase;
import com.bmac.store.ports.out.batch.BatchActivityCreatePort;
import com.bmac.store.ports.out.batch.BatchActivityLoadPort;
import com.bmac.store.ports.out.order.LoadStoreOrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultCancelStoreOrderUseCase implements CancelStoreOrderUseCase {

    private final LoadStoreOrderPort orderLoader;
    private final BatchActivityCreatePort batchActivityCreator;
    private final BatchActivityLoadPort batchActivityLoader;

    @Autowired
    public DefaultCancelStoreOrderUseCase(LoadStoreOrderPort orderLoader,
                                          BatchActivityCreatePort batchActivityCreator,
                                          BatchActivityLoadPort batchActivityLoader) {
        this.orderLoader = orderLoader;
        this.batchActivityCreator = batchActivityCreator;
        this.batchActivityLoader = batchActivityLoader;
    }

    @Override
    public void cancel(CancelStoreOrderCommand command) {
        StoreOrder order = orderLoader.loadById(command.id()).orElseThrow(
                () -> new EntityNotFoundException(StoreOrder.class, UUID.class, command.id().toString())
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
