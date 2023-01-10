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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class BatchForwardedPublisher implements BatchForwardPort {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final EventType eventType = EventType.StoreBatchForwarded;
    private final ApplicationEventPublisher eventPublisher;
    private final RabbitTemplate template;
    private final ObjectMapper objectMapper;

    @Autowired
    public BatchForwardedPublisher(ApplicationEventPublisher eventPublisher, RabbitTemplate template, ObjectMapper objectMapper) {
        this.eventPublisher = eventPublisher;
        this.template = template;
        this.objectMapper = objectMapper;
    }

    @Override
    public void forward(Batch batch, List<Order> orders) throws JsonProcessingException {
        log.debug("Publishing " + eventType.name() + " via fanout exchange");

        List<BatchForwardedEvent.OrderLineItem> orderLineItems = orders.stream()
                .map(order -> new BatchForwardedEvent.OrderLineItem(order.getId(), order.getTimestamp(), order.getOrderedProductsWithUUIDKey()))
                .toList();

        template.convertAndSend(
                AMQPExchangeConfiguration.FANOUT,
                "",
                objectMapper.writeValueAsString(
                        new EventMessage(
                                new EventHeader(
                                        UUID.randomUUID(),
                                        eventType,
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
    }
}
