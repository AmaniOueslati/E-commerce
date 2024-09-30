package com.amani.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public record CustomerRequest(

             String id,

             @NotNull(message = "Customer first name is required")
             String fisrtname,
             @NotNull(message = "Customer last name is required")
             String lastname,
             @NotNull(message = "Customer email is required")
             @Email(message = "customer email is not valid email address ")
             String email,
             @NotNull(message = "Customer adress is required")
             Adress adress
) {
}
