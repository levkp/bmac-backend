//package com.bmac.store.adapters.out.amqp;
//
//import com.bmac.store.ports.out.batch.BatchActivityCreatePort;
//import com.bmac.store.ports.out.batch.BatchForwardPort;
//import com.bmac.store.ports.out.batch.BatchLoadPort;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.context.ApplicationEventPublisher;
//
//import java.util.UUID;
//
//public class BatchDailyForwardPublisher implements BatchForwardPort {
//
//    private ApplicationEventPublisher eventPublisher;
//    private RabbitTemplate template;
//    private BatchActivityCreatePort batchActivityCreatePort;
//    private BatchLoadPort batchLoadPort;
//
//    @Override
//    public void forward(UUID uuid) {
//
//
//    }
//}
