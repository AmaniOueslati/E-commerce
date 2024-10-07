package com.amani.ecommerce.kafka;

import com.amani.ecommerce.customer.CustomerResponse;
import com.amani.ecommerce.order.PaymentMethod;
import com.amani.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

// the data or the information that we need to put or to send to our kafka broker
public record OrderConfirmation (
// what we need to send to our notification service
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}


