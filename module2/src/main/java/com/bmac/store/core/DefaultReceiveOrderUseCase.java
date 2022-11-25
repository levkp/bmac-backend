package com.bmac.store.core;

import com.bmac.store.ports.in.ReceiveOrderCommandMVP;
import com.bmac.store.ports.in.ReceiveOrderUseCase;
import com.bmac.store.ports.out.OrderReceivePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultReceiveOrderUseCase implements ReceiveOrderUseCase {

    OrderReceivePort orderReceiver;

    @Autowired
    public DefaultReceiveOrderUseCase(OrderReceivePort orderReceiver) {
        this.orderReceiver = orderReceiver;
    }

    @Override
    public void receive(ReceiveOrderCommandMVP command) {


    }
}
