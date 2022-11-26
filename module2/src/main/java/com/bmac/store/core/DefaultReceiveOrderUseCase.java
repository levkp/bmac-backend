package com.bmac.store.core;

import com.bmac.store.domain.Batch;
import com.bmac.store.ports.in.ReceiveOrderCommandMVP;
import com.bmac.store.ports.in.ReceiveOrderUseCase;
import com.bmac.store.ports.out.BatchCreatePort;
import com.bmac.store.ports.out.BatchLoadPort;
import com.bmac.store.ports.out.OrderReceivePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class DefaultReceiveOrderUseCase implements ReceiveOrderUseCase {
    private final OrderReceivePort orderReceiver;

    private final BatchLoadPort batchLoader;

    @Autowired
    public DefaultReceiveOrderUseCase(OrderReceivePort orderReceiver, BatchLoadPort batchLoader) {
        this.orderReceiver = orderReceiver;
        this.batchLoader = batchLoader;
    }

    @Override
    public void receive(ReceiveOrderCommandMVP command) {

//        Optional<Batch> optional = batchLoader.loadByDateTime(Loca)


    }
}
