package com.amani.ecommerce.payement;


import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayement(PaymentRequest request) {
    return  Payment.builder()
            .id(request.id())
            .orderId(request.orderId())
            .paymentMethod(request.paymentMethod())
            .amount(request.amount())
            .build();
    }
}
