package com.amani.ecommerce.payment;

import com.amani.ecommerce.customer.CustomerResponse;
import com.amani.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}