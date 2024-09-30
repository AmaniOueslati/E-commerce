package com.amani.ecommerce.customer;

import org.springframework.data.annotation.Id;

public record CustomerResponse(

     String id,
      String fisrtname,
      String lastname,
       String email,
       Adress adress
) {
}
