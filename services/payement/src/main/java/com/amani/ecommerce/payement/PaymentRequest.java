package com.amani.ecommerce.payement;

import java.math.BigDecimal;

public record PaymentRequest(
Integer id ,
BigDecimal amount ,

PaymentMethod paymentMethod ,
Integer orderId ,
String orderReference ,
Customer customer


){
}