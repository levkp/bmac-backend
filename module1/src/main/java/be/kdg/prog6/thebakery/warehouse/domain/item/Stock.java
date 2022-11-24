package be.kdg.prog6.thebakery.warehouse.domain.item;

import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings("unused")
public class Stock {
    Item item;
    Optional<LocalDate> expiry;
    double amount;
}
