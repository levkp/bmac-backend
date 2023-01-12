package com.bmac.warehouse.adapters.out.jpa.order;

import com.bmac.warehouse.domain.InboundOrder;
import com.bmac.warehouse.ports.out.order.CreateOrderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InboundOrderJpaRepositoryAdapter implements CreateOrderPort {
    private final InboundOrderRepository repository;

    @Autowired
    public InboundOrderJpaRepositoryAdapter(InboundOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(InboundOrder inboundOrder) {
        repository.save(toJpaEntity(inboundOrder));
        System.out.println("breakpoint");
    }

    private InboundOrderJpaEntity toJpaEntity(InboundOrder inboundOrder) {
        return new InboundOrderJpaEntity(
                inboundOrder.getId(),
                inboundOrder.getBatchId(),
                inboundOrder.getDate(),
                inboundOrder.getReceived(),
                inboundOrder.getNeededItems());
    }
}
