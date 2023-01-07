package com.bmac.store.adapters.out.amqp;

import com.bmac.common.events.BatchForwardedEvent;
import com.bmac.common.events.EventHeader;
import com.bmac.common.events.EventMessage;
import com.bmac.common.events.EventType;
import com.bmac.store.config.AMQPExchangeConfiguration;
import com.bmac.store.domain.Batch;
import com.bmac.store.domain.Order;
import com.bmac.store.ports.out.batch.BatchForwardPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Profile("amqp")
@Component
public class BatchForwardPublisher implements BatchForwardPort {

    private final ApplicationEventPublisher eventPublisher;
    private final RabbitTemplate template;
    private final ObjectMapper objectMapper;

    @Autowired
    public BatchForwardPublisher(ApplicationEventPublisher eventPublisher, RabbitTemplate template, ObjectMapper objectMapper) {
        this.eventPublisher = eventPublisher;
        this.template = template;
        this.objectMapper = objectMapper;
    }

    @Override
    public void forward(Batch batch, List<Order> orders) throws JsonProcessingException {
        List<BatchForwardedEvent.OrderLineItem> orderLineItems = new ArrayList<>();

        for(Order order : orders) {
            orderLineItems.add(new BatchForwardedEvent.OrderLineItem(order.getId(), order.getTimestamp(), order.getOrderedProductsWithUUIDKey()));
        }


        template.convertAndSend(
                AMQPExchangeConfiguration.fanoutExchange,
                "",
                objectMapper.writeValueAsString(
                        new EventMessage(
                                new EventHeader(
                                        UUID.randomUUID(),
                                        EventType.StoreBatchForwarded,
                                        LocalDateTime.now()),
                                objectMapper.valueToTree(
                                        new BatchForwardedEvent(
                                                batch.getId(),
                                                batch.getDate(),
                                                orderLineItems
                                        )
                                )
                        )
                ));

        System.out.println("Massive success");
    }
}
