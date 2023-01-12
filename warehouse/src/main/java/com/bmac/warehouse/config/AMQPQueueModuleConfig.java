//package com.bmac.warehouse.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AMQPQueueModuleConfig {
//    public static final String FANOUT = "warehouse.fanoutqueue";
//
//    @Bean
//    Queue fanoutQueue() {
//        return QueueBuilder.nonDurable(FANOUT).build();
//    }
//
//    @Bean
//    Binding fanoutBinding(Queue fanoutQueue, @Autowired(required = false) FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
//    }
//}
