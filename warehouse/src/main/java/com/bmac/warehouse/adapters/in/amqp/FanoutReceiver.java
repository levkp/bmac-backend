package com.bmac.warehouse.adapters.in.amqp;

import com.bmac.common.events.EventMessage;
import com.bmac.warehouse.config.AMQPExchangeConfiguration;
import com.bmac.warehouse.config.AMQPQueueConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FanoutReceiver {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final List<AMQPReceiver> receivers;
    private final ObjectMapper objectMapper;

    public FanoutReceiver(List<AMQPReceiver> receivers, ObjectMapper objectMapper) {
        this.receivers = receivers;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = AMQPQueueConfiguration.FANOUT)
    public void receive(String rawMessage) throws JsonProcessingException {
        EventMessage message = objectMapper.readValue(rawMessage, EventMessage.class);

        log.debug("Receiving " + message.eventHeader().type() + " via fanout exchange");

        receivers.stream()
                .filter(r -> r.apply(message.eventHeader().type()))
                .forEach(r -> {
                    try {
                        r.receive(message.messageBody());
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
