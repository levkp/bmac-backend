package com.bmac.warehouse.adapters.in.amqp;

import com.bmac.common.events.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface AMQPReceiver {
    boolean apply(EventType eventType);

    void receive(JsonNode body) throws JsonProcessingException;
}
