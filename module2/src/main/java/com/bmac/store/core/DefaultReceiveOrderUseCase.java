package com.bmac.store.core;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.store.core.exception.StoreEntityNotFoundException;
import com.bmac.store.domain.*;
import com.bmac.store.ports.in.order.ReceiveOrderCommand;
import com.bmac.store.ports.in.order.ReceiveOrderUseCase;
import com.bmac.store.ports.out.batch.BatchActivityCreatePort;
import com.bmac.store.ports.out.batch.BatchCreatePort;
import com.bmac.store.ports.out.batch.BatchLoadPort;
import com.bmac.store.ports.out.batch.BatchUpdatePort;
import com.bmac.store.ports.out.product.ProductLoadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultReceiveOrderUseCase implements ReceiveOrderUseCase {

    private final BatchLoadPort batchLoader;

    private final BatchCreatePort batchCreator;

    private final BatchActivityCreatePort batchActivityCreator;

    private final BatchUpdatePort batchUpdater;
    private final ProductLoadPort productLoader;

    @Autowired
    public DefaultReceiveOrderUseCase(BatchLoadPort batchLoader,
                                      BatchCreatePort batchCreator,
                                      BatchActivityCreatePort batchActivityCreator,
                                      BatchUpdatePort batchUpdater,
                                      ProductLoadPort productLoader) {
        this.batchLoader = batchLoader;
        this.batchCreator = batchCreator;
        this.batchActivityCreator = batchActivityCreator;
        this.batchUpdater = batchUpdater;
        this.productLoader = productLoader;
    }

    @Override
    public void receive(ReceiveOrderCommand command) {
        Map<Product, Integer> orderLine = loadProducts(command.orderLine());
        LocalDate date = DailyCutoffTime.hasPassed() ? LocalDate.now().plusDays(1) : LocalDate.now();
        Optional<Batch> optional = batchLoader.loadByDateTime(date);
        Batch batch;

        if (optional.isEmpty()) {
            batch = new Batch(UUID.randomUUID(), date, LocalDateTime.now(), new BatchActivityWindow());
            batchCreator.create(batch);
        } else {
            batch = optional.get();
        }

        BatchActivity activity = batch.addOrder(new Order(UUID.randomUUID(), batch, LocalDateTime.now(), orderLine));
        batchActivityCreator.create(batch.getId(), activity);
    }

    private Map<Product, Integer> loadProducts(Map<UUID, Integer> orderLine) {
        Map<Product, Integer> loadedOrderLine = new HashMap<>();

        for(Map.Entry<UUID, Integer> item : orderLine.entrySet()) {
            Optional<Product> optional = productLoader.load(item.getKey());
            if (optional.isEmpty()) {
                throw new StoreEntityNotFoundException(Product.class, UUID.class, item.getKey().toString());
            }
            loadedOrderLine.put(optional.get(), item.getValue());
        }

        return loadedOrderLine;
    }
}
