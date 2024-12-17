package com.viggad.payment.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "Firstname is required")
        String firstName,
        @NotNull(message = "Lastname is required")
        String lastName,

        @Email(message = "Email is not correctly formatted")
        @NotNull(message = "Email is required")
        String email
) {
}
