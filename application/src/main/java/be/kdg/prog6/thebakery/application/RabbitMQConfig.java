package be.kdg.prog6.thebakery.application;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("bmac-fanout");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("bmac-topic");
    }

}
