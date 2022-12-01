package com.bmac.store.core;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.store.domain.*;
import com.bmac.store.ports.in.order.ReceiveOrderCommand;
import com.bmac.store.ports.in.order.ReceiveOrderUseCase;
import com.bmac.store.ports.out.batch.BatchActivityCreatePort;
import com.bmac.store.ports.out.batch.BatchCreatePort;
import com.bmac.store.ports.out.batch.BatchLoadPort;
import com.bmac.store.ports.out.batch.BatchUpdatePort;
import com.bmac.store.ports.out.order.OrderReceivePort;
import com.bmac.store.ports.out.product.ProductLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultReceiveOrderUseCase implements ReceiveOrderUseCase {
    private OrderReceivePort orderReceiver;

    private BatchLoadPort batchLoader;

    private BatchCreatePort batchCreator;

    private BatchActivityCreatePort batchActivityCreator;

    private BatchUpdatePort batchUpdater;

    private ProductLoadPort productLoader;


//    @Autowired
//    public DefaultReceiveOrderUseCase(OrderReceivePort orderReceiver,
//                                      BatchLoadPort batchLoader,
//                                      BatchCreatePort batchCreator,
//                                      ProductLoadPort productLoader) {
//        this.orderReceiver = orderReceiver;
//        this.batchLoader = batchLoader;
//        this.batchCreator = batchCreator;
//        this.productLoader = productLoader;
//    }

    @Override
    public void receive(ReceiveOrderCommand command) {
        LocalDate date = DailyCutoffTime.hasPassed() ? LocalDate.now().plusDays(1) : LocalDate.now();
        Optional<Batch> optional = batchLoader.loadByDateTime(date);
        Batch batch;

        if (optional.isEmpty()) {
            batch = new Batch(UUID.randomUUID(), date, LocalDateTime.now(), new BatchActivityWindow());
            batchCreator.create(batch);
        } else {
            batch = optional.get();
        }

        BatchActivity activity = batch.addOrder(command.order());
        batchActivityCreator.create(batch.getUuid(), activity);
    }
}
