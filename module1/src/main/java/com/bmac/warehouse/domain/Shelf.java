package com.bmac.warehouse.domain;

import com.bmac.warehouse.domain.Stock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shelf {
    String id;
    List<Stock> content = new ArrayList<>();

    private Shelf() { }

    public Shelf(String id) {
        this.id = id;
    }

    public boolean addStock(Stock stock) {
        return content.add(stock);
    }

    public LocalDate getClosestExpiry() {
        return LocalDate.now();
    }
}
