package com.amani.notification.notification;

import com.amani.notification.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface NotificationRepository extends MongoRepository<Notification , String> {
}
