package com.bmac.warehouse.adapters.in.amqp;

import com.bmac.common.events.BatchForwardedEvent;
import com.bmac.common.events.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BatchForwardedReceiver implements AMQPReceiver {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final EventType eventType = EventType.StoreBatchForwarded;

    private final ObjectMapper objectMapper;

    public BatchForwardedReceiver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean apply(EventType eventType) {
        return eventType.equals(this.eventType);
    }

    @Override
    public void receive(JsonNode message) throws JsonProcessingException {
        log.info("Receiving" + eventType.name() + " event");

        BatchForwardedEvent event = objectMapper.treeToValue(message, BatchForwardedEvent.class);


    }
}
