package be.kdg.prog6.thebakery.warehouse.domain;

import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings("unused")
public class Stock {
    Ingredient ingredient;
    Optional<LocalDate> expiry;
    double amount;

}
