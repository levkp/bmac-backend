package com.bmac.store.adapters.out.amqp;

import com.bmac.store.domain.Batch;
import com.bmac.store.domain.Order;
import com.bmac.store.ports.out.BatchForwardPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForwardBatchPublisher implements BatchForwardPort {

    private ApplicationEventPublisher eventPublisher;
    private RabbitTemplate template;

    @Override
    public void forward(Batch batch, List<Order> orderLine) {

        System.out.println("Massive success");

    }
}
