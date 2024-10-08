package com.amani.notification.kafka.order;

import com.amani.notification.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
String orderReference ,
BigDecimal totalAmount ,
PaymentMethod paymentMethod ,
Customer customer ,
List<Product> products
) {
}
