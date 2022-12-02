//package com.bmac.store.config;
//
//
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MessageQueueConfiguration {
//    public static final String fanoutExchange = "bmac-fanout";
//    public static final String topicExchange = "bmac-topic";
//
//    public static final String dlqExchange = "dlq-direct";
//
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange(fanoutExchange);
//    }
//
//    @Bean
//    TopicExchange topicExchange() {
//        return new TopicExchange(topicExchange);
//    }
//
//
//    @Bean
//    DirectExchange dlqExchange() { return new DirectExchange(dlqExchange);}
//
//}
