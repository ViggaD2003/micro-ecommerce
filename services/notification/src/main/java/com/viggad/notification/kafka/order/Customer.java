package com.viggad.notification.kafka.order;

import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
