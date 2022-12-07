package com.bmac.store.core;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.common.domain.Product;
import com.bmac.store.core.exception.StoreEntityNotFoundException;
import com.bmac.store.domain.*;
import com.bmac.store.ports.in.order.ReceiveOrderCommand;
import com.bmac.store.ports.in.order.ReceiveOrderUseCase;
import com.bmac.store.ports.out.batch.BatchActivityCreatePort;
import com.bmac.store.ports.out.batch.BatchCreatePort;
import com.bmac.store.ports.out.batch.BatchLoadPort;
import com.bmac.store.ports.out.order.OrderCreatePort;
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

    private final OrderCreatePort orderCreator;
    private final ProductLoadPort productLoader;

    @Autowired
    public DefaultReceiveOrderUseCase(BatchLoadPort batchLoader,
                                      BatchCreatePort batchCreator,
                                      BatchActivityCreatePort batchActivityCreator,
                                      OrderCreatePort orderCreator,
                                      ProductLoadPort productLoader) {
        this.batchLoader = batchLoader;
        this.batchCreator = batchCreator;
        this.batchActivityCreator = batchActivityCreator;
        this.orderCreator = orderCreator;
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

        Order order = new Order(UUID.randomUUID(), batch, LocalDateTime.now(), orderLine);
        orderCreator.create(order);
        BatchActivity activity = batch.addOrder(order);
        batchActivityCreator.create(batch.getId(), activity);
    }

    private Map<Product, Integer> loadProducts(Map<UUID, Integer> orderLine) {
        Map<Product, Integer> loadedOrderLine = new HashMap<>();

        for(Map.Entry<UUID, Integer> item : orderLine.entrySet()) {
            Product product = productLoader.load(item.getKey()).orElseThrow(
                    () -> new StoreEntityNotFoundException(Product.class, UUID.class, item.getKey().toString())
            );
            loadedOrderLine.put(product, item.getValue());
        }

        return loadedOrderLine;
    }
}
