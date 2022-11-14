package be.kdg.prog6.thebakery.warehouse.domain.order;

import be.kdg.prog6.thebakery.warehouse.domain.Stock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shipment {
    LocalDate arrival;
    List<Stock> shipmentLine = new ArrayList<>();
}
