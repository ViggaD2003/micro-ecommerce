package com.viggad.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @NotNull(message = "Quantity is mandatory")
        Double quantity
) {
}
