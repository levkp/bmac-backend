package com.bmac.warehouse.ports.out.order;

import com.bmac.warehouse.domain.InboundOrder;

public interface CreateOrderPort {
    void create(InboundOrder inboundOrder);
}
