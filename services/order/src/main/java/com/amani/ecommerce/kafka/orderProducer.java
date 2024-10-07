package com.amani.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class orderProducer {

// the key is type String nd the object that we want to send is of type orderConfirmation

private final KafkaTemplate<String , OrderConfirmation > kafkaTemplate;

public void sendOrderConfirmation( OrderConfirmation orderConfirmation){

log.info("Sending order confirmation");
 // the message must be from import org.springframework.messaging.Message;
    //in the message we want to send the object of OrderCinfirmation
    Message<OrderConfirmation> message = MessageBuilder
            .withPayload(orderConfirmation) //load information of the object
            .setHeader(KafkaHeaders.TOPIC , "order-topic")// to which topic that we want to send the onformation
            .build();
    kafkaTemplate.send(message); 

}
}
