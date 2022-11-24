package be.kdg.prog6.thebakery.warehouse.domain.order.outbound;

import be.kdg.prog6.thebakery.warehouse.domain.order.Order;
import be.kdg.prog6.thebakery.warehouse.domain.order.inbound.Shipment;

import java.util.List;

public class OutboundOrder extends Order {
    Delivery delivery;
}
