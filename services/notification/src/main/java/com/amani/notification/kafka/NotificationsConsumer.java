package com.amani.notification.kafka;

import com.amani.notification.email.EmailService;
import com.amani.notification.kafka.order.OrderConfirmation;
import com.amani.notification.kafka.payment.PaymentConfirmation;
import com.amani.notification.notification.Notification;
import com.amani.notification.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.amani.notification.notification.NotificationType.ORDER_CONFIRMATION;
import static com.amani.notification.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationsConsumer {

    private final NotificationRepository repository;
   private final EmailService emailService;


    //consume our payment topic
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
        Notification.builder()
                .type(PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build()
        );
        // send email to our customer
      var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
      emailService.sendPaymentSuccessEmail(
        paymentConfirmation.customerEmail(),
         customerName,
       paymentConfirmation.amount(),
    paymentConfirmation.orderReference()
     );
    }

   // consume from  order topic
    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}