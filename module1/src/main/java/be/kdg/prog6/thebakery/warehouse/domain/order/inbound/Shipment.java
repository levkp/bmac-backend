package be.kdg.prog6.thebakery.warehouse.domain.order.inbound;

import be.kdg.prog6.thebakery.warehouse.domain.item.Stock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shipment {
    LocalDate departure;
    LocalDate arrival;
    List<Stock> shipmentLine = new ArrayList<>();
}
