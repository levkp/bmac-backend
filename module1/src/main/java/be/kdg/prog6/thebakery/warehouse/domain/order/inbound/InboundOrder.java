package be.kdg.prog6.thebakery.warehouse.domain.order.inbound;

import be.kdg.prog6.thebakery.warehouse.domain.order.Order;

import java.util.List;

public class InboundOrder extends Order {
    List<Shipment> shipments;
}
