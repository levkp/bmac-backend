package com.bmac.warehouse.ports.out;

import com.bmac.warehouse.domain.InboundOrder;

public interface CreateOrderPort {
    void create(InboundOrder inboundOrder);
}
