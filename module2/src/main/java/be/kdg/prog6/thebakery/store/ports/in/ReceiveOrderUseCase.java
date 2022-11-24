package be.kdg.prog6.thebakery.store.ports.in;

public interface ReceiveOrderUseCase {
    void receive(ReceiveOrderCommandMVP command);
}
