package com.bmac.store.core;

import com.bmac.store.ports.in.ReceiveOrderCommandMVP;
import com.bmac.store.ports.in.ReceiveOrderUseCase;
import org.springframework.stereotype.Service;

@Service
public class DefaultReceiveOrderUseCase implements ReceiveOrderUseCase {

    @Override
    public void receive(ReceiveOrderCommandMVP command) {

    }
}
