package be.kdg.prog6.thebakery.store.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private final String name;
    private final LocalDate clientSince;

    public Customer(String name) {
        id = UUID.randomUUID();
        this.name = name;
        clientSince = LocalDate.now();
    }
}
