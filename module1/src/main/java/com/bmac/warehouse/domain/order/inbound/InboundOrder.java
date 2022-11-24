package com.bmac.warehouse.domain.order.inbound;

import com.bmac.warehouse.domain.order.Order;

import java.util.List;

public class InboundOrder extends Order {
    List<Shipment> shipments;
}
