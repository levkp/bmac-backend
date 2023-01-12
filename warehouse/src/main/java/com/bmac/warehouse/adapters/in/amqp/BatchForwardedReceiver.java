//package com.bmac.warehouse.adapters.in.amqp;
//
//import com.bmac.common.events.BatchForwardedEvent;
//import com.bmac.common.events.EventType;
//import com.bmac.warehouse.ports.in.order.ReceiveOrderUseCase;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BatchForwardedReceiver implements AMQPReceiver {
//    private final Logger log = LoggerFactory.getLogger(getClass());
//    private final EventType eventType = EventType.StoreBatchForwarded;
//    private final ObjectMapper objectMapper;
//    private final ReceiveOrderUseCase receiveOrder;
//
//    public BatchForwardedReceiver(ObjectMapper objectMapper, ReceiveOrderUseCase receiveOrder) {
//        this.objectMapper = objectMapper;
//        this.receiveOrder = receiveOrder;
//    }
//
//    @Override
//    public boolean apply(EventType eventType) {
//        return eventType.equals(this.eventType);
//    }
//
//    @Override
//    public void receive(JsonNode message) throws JsonProcessingException {
//        BatchForwardedEvent event = objectMapper.treeToValue(message, BatchForwardedEvent.class);
////        log.debug(String.format("Receiving %s with batch attributes UUID: %s Date: %s Size: %d orders",
////                eventType.name(), event.batchId(), event.batchDate(), event.orderLine().size()));
//        receiveOrder.receive(event);
//    }
//}
