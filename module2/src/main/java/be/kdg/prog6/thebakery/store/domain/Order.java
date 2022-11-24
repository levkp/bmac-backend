package be.kdg.prog6.thebakery.store.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    private final LocalDateTime timestamp;
    private final Map<Product, Integer> orderLine;
    private final UUID id;
    private final Customer customer;

    public Order(Customer customer) {
        id = UUID.randomUUID();
        timestamp = LocalDateTime.now();
        orderLine = new HashMap<>();
        this.customer = customer;
//        status = new OrderStatus(LocalDateTime.now(), OrderStatus.Status.RECEIVED);
    }
}
