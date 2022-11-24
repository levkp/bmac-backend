package com.bmac.warehouse.domain.order.inbound;

import com.bmac.warehouse.domain.item.Stock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shipment {
    LocalDate departure;
    LocalDate arrival;
    List<Stock> shipmentLine = new ArrayList<>();
}
