package com.bmac.store.adapters.out.amqp;

import com.bmac.store.ports.out.BatchActivityCreatePort;
import com.bmac.store.ports.out.BatchDailyForwardPort;
import com.bmac.store.ports.out.BatchLoadPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;

public class BatchDailyForwardPublisher {

    private ApplicationEventPublisher eventPublisher;
    private RabbitTemplate template;
    private BatchActivityCreatePort batchActivityCreatePort;
    private BatchLoadPort batchLoadPort;


}
