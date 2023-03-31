package io.wdefassio.msavaliationcredit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Value("${mq.queues.emission-card}")
    private String queueName;

    @Bean
    public Queue queueEmissionCard() {
        return new Queue(queueName, true);
    }


}
