package com.bmac.warehouse.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPQueueConfiguration {
    public static final String FANOUT = "warehouse.fanoutqueue";

    @Bean
    Queue fanoutQueue() {
        return QueueBuilder.nonDurable(FANOUT).build();
    }

    @Bean
    Binding fanoutBinding(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }
}
