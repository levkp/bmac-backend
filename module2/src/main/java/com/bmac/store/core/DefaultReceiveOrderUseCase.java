package com.bmac.store.core;

import com.bmac.common.cutoff.DailyCutoffTime;
import com.bmac.store.core.exception.ProductNotFoundException;
import com.bmac.store.domain.Batch;
import com.bmac.store.domain.Order;
import com.bmac.store.domain.Product;
import com.bmac.store.ports.in.ReceiveOrderCommandMVP;
import com.bmac.store.ports.in.ReceiveOrderUseCase;
import com.bmac.store.ports.out.BatchCreatePort;
import com.bmac.store.ports.out.BatchLoadPort;
import com.bmac.store.ports.out.OrderReceivePort;
import com.bmac.store.ports.out.ProductLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DefaultReceiveOrderUseCase implements ReceiveOrderUseCase {
    private final OrderReceivePort orderReceiver;

    private final BatchLoadPort batchLoader;

    private final BatchCreatePort batchCreator;

    private final ProductLoadPort productLoader;

    @Autowired
    public DefaultReceiveOrderUseCase(OrderReceivePort orderReceiver,
                                      BatchLoadPort batchLoader,
                                      BatchCreatePort batchCreator,
                                      ProductLoadPort productLoader) {
        this.orderReceiver = orderReceiver;
        this.batchLoader = batchLoader;
        this.batchCreator = batchCreator;
        this.productLoader = productLoader;
    }

    @Override
    public void receive(ReceiveOrderCommandMVP command) {
        Optional<Product> productOptional = productLoader.load(command.productID());
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException(command.productID());
        }

        LocalDate batchDate = DailyCutoffTime.hasPassed() ? LocalDate.now().plusDays(1) : LocalDate.now();
        Optional<Batch> batchOptional = batchLoader.loadByDateTime(batchDate);
        Batch batch = batchOptional.isEmpty() ? batchCreator.create(batchDate) : batchOptional.get();

        orderReceiver.receive(new Order(batch, productOptional.get(), command.amount()));
    }
}
