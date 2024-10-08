package com.amani.ecommerce.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaPaymentTopic {

// this is the topic we create in kafka in order to send all the payement confirmations
@Bean
public NewTopic payementTopic(){

return TopicBuilder
        .name("payment-topic")
        .build();
}
}
