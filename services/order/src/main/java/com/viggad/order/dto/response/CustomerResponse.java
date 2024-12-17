package com.viggad.order.dto.response;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
