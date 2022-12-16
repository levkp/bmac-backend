package com.bmac.warehouse.domain;

import java.time.LocalDate;
import java.util.UUID;

@SuppressWarnings("unused")
public class Stock {
    private UUID id;
    private Item item;
    private Shelf location;
    private LocalDate expiry;
    private double amount;

    public Stock(Item item, Shelf location, LocalDate expiry, double amount) {
        this.item = item;
        this.location = location;
        this.expiry = expiry;
        this.amount = amount;
    }
}
