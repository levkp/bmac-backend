//package com.bmac.warehouse.config;
//
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AMQPExchangeModuleConfig {
//    public static final String FANOUT = "bmac.fanout";
//    public static final String TOPIC = "bmac.topic";
//
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange(FANOUT);
//    }
//
//    @Bean
//    TopicExchange topicExchange() {
//        return new TopicExchange(TOPIC);
//    }
//}
//
