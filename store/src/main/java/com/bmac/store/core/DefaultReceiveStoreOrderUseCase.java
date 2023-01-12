package com.bmac.store.core;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.store.domain.StoreProduct;
import com.bmac.common.exception.EntityNotFoundException;
import com.bmac.store.domain.*;
import com.bmac.store.ports.in.order.ReceiveStoreOrderCommand;
import com.bmac.store.ports.in.order.ReceiveStoreOrderUseCase;
import com.bmac.store.ports.out.batch.BatchActivityCreatePort;
import com.bmac.store.ports.out.batch.BatchCreatePort;
import com.bmac.store.ports.out.batch.BatchLoadPort;
import com.bmac.store.ports.out.order.CreateStoreOrderPort;
import com.bmac.store.ports.out.product.LoadStoreProductPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultReceiveStoreOrderUseCase implements ReceiveStoreOrderUseCase {

    private final BatchLoadPort batchLoader;
    private final BatchCreatePort batchCreator;
    private final BatchActivityCreatePort batchActivityCreator;
    private final CreateStoreOrderPort orderCreator;
    private final LoadStoreProductPort productLoader;

    @Autowired
    public DefaultReceiveStoreOrderUseCase(BatchLoadPort batchLoader,
                                           BatchCreatePort batchCreator,
                                           BatchActivityCreatePort batchActivityCreator,
                                           CreateStoreOrderPort orderCreator,
                                           LoadStoreProductPort productLoader) {
        this.batchLoader = batchLoader;
        this.batchCreator = batchCreator;
        this.batchActivityCreator = batchActivityCreator;
        this.orderCreator = orderCreator;
        this.productLoader = productLoader;
    }

    @Override
    public UUID receive(ReceiveStoreOrderCommand command) {
        Map<StoreProduct, Integer> orderLine = loadProducts(command.orderLine());
        LocalDate date = DailyCutoffTime.hasPassed() ? LocalDate.now().plusDays(1) : LocalDate.now();
        Optional<Batch> optional = batchLoader.loadByDate(date);
        Batch batch;

        if (optional.isEmpty()) {
            batch = new Batch(UUID.randomUUID(), date, LocalDateTime.now(), new Batch.ActivityWindow());
            batchCreator.create(batch);
        } else {
            batch = optional.get();
        }

        StoreOrder order = new StoreOrder(UUID.randomUUID(), batch, LocalDateTime.now(), orderLine);
        orderCreator.create(order);
        BatchActivity activity = batch.receiveOrder(order);
        batchActivityCreator.create(batch.getId(), activity);

        return order.getId();
    }

    private Map<StoreProduct, Integer> loadProducts(Map<UUID, Integer> orderLine) {
        Map<StoreProduct, Integer> loadedOrderLine = new HashMap<>();

        for(Map.Entry<UUID, Integer> item : orderLine.entrySet()) {
            StoreProduct product = productLoader.loadById(item.getKey()).orElseThrow(
                    () -> new EntityNotFoundException(StoreProduct.class, UUID.class, item.getKey().toString())
            );
            loadedOrderLine.put(product, item.getValue());
        }

        return loadedOrderLine;
    }
}
