package be.kdg.prog6.thebakery.store.core;

import be.kdg.prog6.thebakery.store.ports.in.ReceiveOrderCommandMVP;
import be.kdg.prog6.thebakery.store.ports.in.ReceiveOrderUseCase;
import org.springframework.stereotype.Service;

@Service
public class DefaultReceiveOrderUseCase implements ReceiveOrderUseCase {

    @Override
    public void receive(ReceiveOrderCommandMVP command) {

    }
}
