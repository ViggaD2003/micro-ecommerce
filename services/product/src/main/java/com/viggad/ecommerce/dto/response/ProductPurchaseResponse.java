package com.viggad.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
public record ProductPurchaseResponse(

        Integer productId,
        String name,
        String description,
        BigDecimal price,
        Double quantity
) {
}
