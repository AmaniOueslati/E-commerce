package com.amani.ecommerce.payement;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated // to ensure when create this ibject it's not null
public record Customer(
        String id,
        @NotNull(message = "Firstname is required")
        String firstname,
        @NotNull(message = "Lastname is required")
        String lastname,
        @NotNull(message = "Email is required")
        @Email(message = "The customer email is not correctly formatted")
        String email
) { }